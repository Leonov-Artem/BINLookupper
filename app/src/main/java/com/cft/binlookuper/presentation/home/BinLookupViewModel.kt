package com.cft.binlookuper.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cft.data.model.State
import com.cft.data.usecase.LookupByBinUseCase

private val TAG = BinLookupViewModel::class.java.simpleName

class BinLookupViewModel(
    private val lookupByBinUseCase: LookupByBinUseCase,
) : ViewModel() {

    private val binMutableLiveData = MutableLiveData<String?>()

    val lookupState: LiveData<State>
        get() = Transformations.switchMap(binMutableLiveData) { bin ->
            lookupByBinUseCase.execute(bin)
        }

    fun lookupByBin(bin: String?) {
        binMutableLiveData.value = bin
    }
}