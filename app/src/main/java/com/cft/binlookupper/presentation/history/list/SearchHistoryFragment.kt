package com.cft.binlookupper.presentation.history.list

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.cft.binlookupper.R
import com.cft.binlookupper.databinding.FragmentSearchHistoryBinding
import com.cft.binlookupper.presentation.common.BOOLEAN_KEY_DELETE
import com.cft.binlookupper.presentation.common.REQUEST_KEY_DELETE
import com.cft.binlookupper.presentation.common.addMenu
import com.cft.binlookupper.presentation.common.setActionBarTitle
import com.cft.binlookupper.presentation.history.DeletionAlertDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

private val CLASS_NAME = SearchHistoryFragment::class.java.simpleName
private val INTERFACE_NAME = SearchHistoryFragment.Callbacks::class.java.simpleName
private val TAG = CLASS_NAME

class SearchHistoryFragment :
    Fragment(),
    MenuProvider {

    interface Callbacks {
        fun onSearchHistoryListItemClicked(bin: String)
    }

    private lateinit var callbacks: Callbacks
    private lateinit var binding: FragmentSearchHistoryBinding

    private val viewModel by viewModel<SearchHistoryViewModel>()

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
        setActionBarTitle(R.string.action_bar_title_search_history)
        binding = FragmentSearchHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchHistoryList()
        setDeletionAlertDialogFragmentResultListener()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.fragment_search_history, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_item_delete_all -> {
                showDeleteSearchHistoryDialog()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun showDeleteSearchHistoryDialog() {
        val message = getString(R.string.dialog_message_delete_search_history)
        DeletionAlertDialogFragment.newInstance(message).apply {
            show(this@SearchHistoryFragment.parentFragmentManager, null)
        }
    }

    private fun setDeletionAlertDialogFragmentResultListener() {
        setFragmentResultListener(REQUEST_KEY_DELETE) { _, bundle ->
            val needToDelete = bundle.getBoolean(BOOLEAN_KEY_DELETE)
            if (needToDelete) {
                viewModel.deleteSearchHistory()
            }
        }
    }

    private fun observeSearchHistoryList() {
        viewModel.binList.observe(viewLifecycleOwner) { binList ->
            binding.recyclerView.adapter = BinListAdapter(binList, callbacks)
        }
    }
}