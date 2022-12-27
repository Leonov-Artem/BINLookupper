package com.cft.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cft.data.model.CardMetaData
import com.cft.data.model.CardMetaData.Companion.BIN_COLUMN_NAME
import com.cft.data.model.CardMetaData.Companion.TABLE_NAME

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyItem: CardMetaData)

    @Query("SELECT $BIN_COLUMN_NAME FROM $TABLE_NAME")
    fun getBinList(): LiveData<List<String>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $BIN_COLUMN_NAME = :bin")
    fun getDetailsByBin(bin: String): LiveData<CardMetaData?>

    @Query("DELETE FROM $TABLE_NAME WHERE $BIN_COLUMN_NAME = :bin")
    fun delete(bin: String)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()
}