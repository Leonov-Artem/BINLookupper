package com.cft.binlookuper.presentation.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.cft.binlookuper.R
import com.cft.binlookuper.databinding.FragmentBinLookupBinding
import com.cft.binlookuper.presentation.common.addMenu
import com.cft.binlookuper.presentation.common.setActionBarTitle
import com.cft.data.model.CardMetaData
import com.cft.data.model.State
import org.koin.androidx.viewmodel.ext.android.viewModel

private val CLASS_NAME = BinLookupFragment::class.java.simpleName
private val INTERFACE_NAME = BinLookupFragment.Callbacks::class.java.simpleName
private val TAG = CLASS_NAME

class BinLookupFragment : Fragment(), MenuProvider {

    interface Callbacks {
        fun onSearchHistoryMenuItemClicked()
    }

    private lateinit var callbacks: Callbacks
    private lateinit var binding: FragmentBinLookupBinding
    private val viewModel by viewModel<BinLookupViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callbacks = context as Callbacks
        } catch (e: ClassCastException) {
            val contextName = context::class.java.simpleName
            throw ClassCastException("$contextName must implement $CLASS_NAME.$INTERFACE_NAME")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addMenu()
        setActionBarTitle(R.string.app_name)
        binding = FragmentBinLookupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLookupState()
        setupSearchView()
        setupCoordinatesClickability()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.fragment_bin_lookup, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_item_search_history -> {
                callbacks.onSearchHistoryMenuItemClicked()
                clearSearchQuery()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(queryText: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(queryText: String?): Boolean {
                    viewModel.lookupByBin(bin = queryText)
                    return true
                }
            })
        }
    }

    private fun clearSearchQuery() {
        binding.searchView.setQuery("", true)
    }

    private fun observeLookupState() {
        viewModel.lookupState.observe(viewLifecycleOwner) { state ->
            renderLookupState(state)
        }
    }

    private fun renderLookupState(state: State) {
        binding.progressBar.isVisible = state is State.Loading

        when (state) {
            is State.Loading -> {
                renderLoadingState()
            }
            is State.Success -> {
                renderSuccessState(data = state.data as CardMetaData)
            }
            is State.SpeedLimitExceeded -> {
                renderSpeedLimitExceededState()
            }
            is State.Error -> {
                renderErrorState(errorMessage = state.message)
            }
        }
    }

    private fun renderLoadingState() {
        Log.d(TAG, "State.Loading")
    }

    private fun renderSuccessState(data: CardMetaData) {
        Log.d(TAG, "State.Success, data = $data")
        binding.dataLayout.cardMetaData = data
    }

    private fun renderErrorState(errorMessage: String) {
        Log.d(TAG, "State.Error, message = $errorMessage")
    }

    private fun renderSpeedLimitExceededState() {
        Log.d(TAG, "State.SpeedLimitExceeded")
        showRequestLimitExceededDialog()
    }

    private fun showRequestLimitExceededDialog() {
        SpeedLimitExceededFragmentDialog.newInstance().apply {
            show(this@BinLookupFragment.parentFragmentManager, null)
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