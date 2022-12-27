package com.cft.binlookuper.di

import com.cft.binlookuper.presentation.history.details.SearchHistoryDetailsViewModel
import com.cft.binlookuper.presentation.history.list.SearchHistoryViewModel
import com.cft.binlookuper.presentation.home.BinLookupViewModel
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