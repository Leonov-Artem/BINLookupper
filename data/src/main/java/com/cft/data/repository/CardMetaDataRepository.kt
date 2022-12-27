package com.cft.data.repository

import androidx.lifecycle.LiveData
import com.cft.data.model.State

interface CardMetaDataRepository {

    fun lookupByBin(bin: String?): LiveData<State>
}