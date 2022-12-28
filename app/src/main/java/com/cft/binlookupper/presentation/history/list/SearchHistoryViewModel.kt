package com.cft.binlookupper.presentation.history.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cft.binlookupper.data.usecase.DeleteSearchHistoryUseCase
import com.cft.binlookupper.data.usecase.GetBinListUseCase
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    getBinListUseCase: GetBinListUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase,
) : ViewModel() {

    val binList = getBinListUseCase.execute()

    fun deleteSearchHistory() {
        viewModelScope.launch {
            deleteSearchHistoryUseCase.execute()
        }
    }
}