package com.cft.binlookupper.presentation.history.details

import androidx.lifecycle.*
import com.cft.binlookupper.data.model.CardMetaData
import com.cft.binlookupper.data.usecase.DeleteSearchHistoryItemByBinUseCase
import com.cft.binlookupper.data.usecase.GetDetailsByBinUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val TAG = SearchHistoryDetailsViewModel::class.java.simpleName

class SearchHistoryDetailsViewModel(
    private val getDetailsByBinUseCase: GetDetailsByBinUseCase,
    private val deleteSearchHistoryItemByBinUseCase: DeleteSearchHistoryItemByBinUseCase,
) : ViewModel() {

    private val binMutableLiveData = MutableLiveData<String>()

    val historyItem: LiveData<CardMetaData?>
        get() = Transformations.switchMap(binMutableLiveData) { bin ->
            getDetailsByBinUseCase.execute(bin)
        }

    fun loadDetails(bin: String) {
        binMutableLiveData.value = bin
    }

    fun deleteSearchHistoryItem(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSearchHistoryItemByBinUseCase.execute(bin)
        }
    }
}