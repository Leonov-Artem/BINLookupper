package com.cft.data.usecase

import com.cft.data.repository.SearchHistoryRepository

class DeleteSearchHistoryItemByBinUseCase(
    private val repository: SearchHistoryRepository,
) {

    suspend fun execute(bin: String) = repository.delete(bin)
}