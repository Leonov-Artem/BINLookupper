package com.cft.binlookupper.presentation.history.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs
import com.cft.binlookupper.R
import com.cft.binlookupper.databinding.FragmentSearchHistoryDetailsBinding
import com.cft.binlookupper.presentation.common.BOOLEAN_KEY_DELETE
import com.cft.binlookupper.presentation.common.REQUEST_KEY_DELETE
import com.cft.binlookupper.presentation.common.addMenu
import com.cft.binlookupper.presentation.common.setActionBarTitle
import com.cft.binlookupper.presentation.history.DeletionAlertDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

private val CLASS_NAME = SearchHistoryDetailsFragment::class.java.simpleName
private val INTERFACE_NAME = SearchHistoryDetailsFragment.Callbacks::class.java.simpleName
private val TAG = CLASS_NAME

class SearchHistoryDetailsFragment : Fragment(), MenuProvider {

    interface Callbacks {
        fun onSearchHistoryItemDeleted()
    }

    private lateinit var callbacks: Callbacks
    private lateinit var binding: FragmentSearchHistoryDetailsBinding

    private val args by navArgs<SearchHistoryDetailsFragmentArgs>()
    private val viewModel by viewModel<SearchHistoryDetailsViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callbacks = context as Callbacks
        } catch (e: ClassCastException) {
            val contextName = context::class.java.simpleName
            throw ClassCastException("$contextName must implement $CLASS_NAME.$INTERFACE_NAME")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails(args.bin)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addMenu()
        setActionBarTitle(args.bin)
        binding = FragmentSearchHistoryDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchHistoryItem()
        setDeletionAlertDialogFragmentResultListener()
        setupCoordinatesClickability()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.fragment_search_history_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_item_delete -> {
                showDeleteSearchHistoryItemDialog()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun showDeleteSearchHistoryItemDialog() {
        val message = getString(R.string.dialog_message_delete_search_history_item)
        DeletionAlertDialogFragment.newInstance(message).apply {
            show(this@SearchHistoryDetailsFragment.parentFragmentManager, null)
        }
    }

    private fun setDeletionAlertDialogFragmentResultListener() {
        setFragmentResultListener(REQUEST_KEY_DELETE) { _, bundle ->
            val needToDelete = bundle.getBoolean(BOOLEAN_KEY_DELETE)
            if (needToDelete) {
                viewModel.deleteSearchHistoryItem(args.bin)
                callbacks.onSearchHistoryItemDeleted()
            }
        }
    }

    private fun observeSearchHistoryItem() {
        viewModel.historyItem.observe(viewLifecycleOwner) { cardMetaData ->
            cardMetaData?.let { data ->
                binding.dataLayout.cardMetaData = data
            }
        }
    }

    private fun setupCoordinatesClickability() {
        binding.dataLayout.apply {
            latitudeTextView.setOnClickListener { goToMap() }
            latitudeLableTextView.setOnClickListener { goToMap() }
            longitudeTextView.setOnClickListener { goToMap() }
            longitudeLabelTextView.setOnClickListener { goToMap() }
        }
    }

    private fun goToMap() {
        binding.dataLayout.apply {
            val latitude = latitudeTextView.text.toString().toFloat()
            val longitude = longitudeTextView.text.toString().toFloat()
            val coordinates = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, coordinates).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            requireContext().startActivity(intent)
        }
    }
}