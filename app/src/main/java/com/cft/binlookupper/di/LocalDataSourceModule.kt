package com.cft.binlookupper.di

import androidx.room.Room
import com.cft.binlookupper.data.source.local.SearchHistoryDatabase
import org.koin.dsl.module

private const val DATABASE_NAME = "search_history_database"

val localDataSourceModule = module {

    single {
        Room.databaseBuilder(
            get(),
            SearchHistoryDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    single {
        get<SearchHistoryDatabase>().searchHistoryDao()
    }
}