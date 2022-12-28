package com.cft.binlookupper.di

import com.cft.binlookupper.data.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        DeleteSearchHistoryItemByBinUseCase(
            repository = get(),
        )
    }

    factory {
        DeleteSearchHistoryUseCase(
            repository = get(),
        )
    }

    factory {
        GetBinListUseCase(
            repository = get(),
        )
    }

    factory {
        GetDetailsByBinUseCase(
            repository = get(),
        )
    }

    factory {
        LookupByBinUseCase(
            repository = get(),
        )
    }
}