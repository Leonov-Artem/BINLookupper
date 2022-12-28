package com.cft.binlookupper.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cft.binlookupper.data.model.CardMetaData

@Database(entities = [CardMetaData::class], version = 1)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}