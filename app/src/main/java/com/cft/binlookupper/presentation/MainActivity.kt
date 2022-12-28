package com.cft.binlookupper.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cft.binlookupper.R
import com.cft.binlookupper.presentation.common.hideActionBarArrow
import com.cft.binlookupper.presentation.common.showActionBarArrow
import com.cft.binlookupper.presentation.history.details.SearchHistoryDetailsFragment
import com.cft.binlookupper.presentation.history.list.SearchHistoryFragment
import com.cft.binlookupper.presentation.history.list.SearchHistoryFragmentDirections
import com.cft.binlookupper.presentation.home.BinLookupFragment
import com.cft.binlookupper.presentation.home.BinLookupFragmentDirections

private val TAG = MainActivity::class.java.simpleName

class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    BinLookupFragment.Callbacks,
    SearchHistoryFragment.Callbacks,
    SearchHistoryDetailsFragment.Callbacks {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavController()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onSearchHistoryMenuItemClicked() {
        navigateToSearchHistoryFromBinLookup()
    }

    override fun onSearchHistoryListItemClicked(bin: String) {
        navigateToDetailsFromSearchHistory(bin)
    }

    override fun onSearchHistoryItemDeleted() {
        navController.navigateUp()
    }

    private fun initNavController() {
        val fragmentContainer = supportFragmentManager.findFragmentById(R.id.fragment_container)
        navController = (fragmentContainer as NavHostFragment).navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.binLookupFragment -> {
                    hideActionBarArrow()
                }
                else -> {
                    showActionBarArrow()
                }
            }
        }
    }

    private fun navigateToSearchHistoryFromBinLookup() {
        val directions = BinLookupFragmentDirections
        val toSearchHistory = directions.actionBinLookupFragmentToSearchHistoryFragment()
        navController.navigate(toSearchHistory)
    }

    private fun navigateToDetailsFromSearchHistory(bin: String) {
        val directions = SearchHistoryFragmentDirections
        val toSearchHistoryDetails =
            directions.actionSearchHistoryFragmentToSearchHistoryDetailsFragment(bin)
        navController.navigate(toSearchHistoryDetails)
    }
}