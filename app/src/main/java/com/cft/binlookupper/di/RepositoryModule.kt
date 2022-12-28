package com.cft.binlookupper.di

import com.cft.binlookupper.data.repository.CardMetaDataRepository
import com.cft.binlookupper.data.repository.CardMetaDataRepositoryImpl
import com.cft.binlookupper.data.repository.SearchHistoryRepository
import com.cft.binlookupper.data.repository.SearchHistoryRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<CardMetaDataRepository> {
        CardMetaDataRepositoryImpl(
            api = get(),
            dao = get(),
        )
    }

    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl(
            dao = get(),
        )
    }
}