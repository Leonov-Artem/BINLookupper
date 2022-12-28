package com.cft.binlookupper.data.repository

import androidx.lifecycle.liveData
import com.cft.binlookupper.data.model.CardMetaData
import com.cft.binlookupper.data.model.State
import com.cft.binlookupper.data.source.local.SearchHistoryDao
import com.cft.binlookupper.data.source.remote.CardMetaDataService

class CardMetaDataRepositoryImpl(
    private val api: CardMetaDataService,
    private val dao: SearchHistoryDao,
) : CardMetaDataRepository {

    override fun lookupByBin(bin: String?) = liveData {
        if (bin.isNullOrBlank()) {
            emit(emptyData())
            return@liveData
        }

        emit(State.Loading)
        try {
            val response = api.lookupByBin(bin)
            if (response.isSuccessful) {
                val lookupResult = response.body()!!.apply { this.bin = bin }
                emit(State.Success(data = lookupResult))
                dao.insert(lookupResult)
                return@liveData
            }

            when (response.code()) {
                404 -> {
                    emit(emptyData())
                    emit(State.Error(message = "404: not found"))
                }
                429 -> {
                    emit(State.SpeedLimitExceeded)
                }
            }
        } catch (ex: Exception) {
            emit(State.Error(message = ex.localizedMessage ?: "Some error occurred"))
        }
    }

    private fun emptyData() = State.Success(data = CardMetaData())
}