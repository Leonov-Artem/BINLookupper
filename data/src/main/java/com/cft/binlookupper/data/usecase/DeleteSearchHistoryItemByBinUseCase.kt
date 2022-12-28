package com.cft.binlookupper.data.usecase

import com.cft.binlookupper.data.repository.SearchHistoryRepository

class DeleteSearchHistoryItemByBinUseCase(
    private val repository: SearchHistoryRepository,
) {

    suspend fun execute(bin: String) = repository.delete(bin)
}