package com.cft.binlookupper.data.repository

import androidx.lifecycle.LiveData
import com.cft.binlookupper.data.model.State

interface CardMetaDataRepository {

    fun lookupByBin(bin: String?): LiveData<State>
}