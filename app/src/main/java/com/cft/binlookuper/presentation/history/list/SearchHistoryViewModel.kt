package com.cft.binlookuper.presentation.history.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cft.data.usecase.DeleteSearchHistoryUseCase
import com.cft.data.usecase.GetBinListUseCase
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