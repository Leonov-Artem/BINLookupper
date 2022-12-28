package com.cft.binlookupper.data.repository

import androidx.lifecycle.LiveData
import com.cft.binlookupper.data.model.CardMetaData

interface SearchHistoryRepository {

    fun getBinList(): LiveData<List<String>>

    fun getDetailsByBin(bin: String): LiveData<CardMetaData?>

    suspend fun delete(bin: String)

    suspend fun deleteAll()
}