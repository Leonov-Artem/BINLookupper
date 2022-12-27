package com.cft.binlookuper.di

import com.cft.data.repository.CardMetaDataRepository
import com.cft.data.repository.CardMetaDataRepositoryImpl
import com.cft.data.repository.SearchHistoryRepository
import com.cft.data.repository.SearchHistoryRepositoryImpl
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