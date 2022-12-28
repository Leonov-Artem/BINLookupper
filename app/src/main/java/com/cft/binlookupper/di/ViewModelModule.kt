package com.cft.binlookupper.di

import com.cft.binlookupper.presentation.history.details.SearchHistoryDetailsViewModel
import com.cft.binlookupper.presentation.history.list.SearchHistoryViewModel
import com.cft.binlookupper.presentation.home.BinLookupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BinLookupViewModel(
            lookupByBinUseCase = get(),
        )
    }

    viewModel {
        SearchHistoryViewModel(
            getBinListUseCase = get(),
            deleteSearchHistoryUseCase = get(),
        )
    }

    viewModel {
        SearchHistoryDetailsViewModel(
            getDetailsByBinUseCase = get(),
            deleteSearchHistoryItemByBinUseCase = get(),
        )
    }
}