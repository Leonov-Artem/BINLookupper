package com.cft.binlookupper.data.usecase

import com.cft.binlookupper.data.repository.SearchHistoryRepository

class DeleteSearchHistoryUseCase(
    private val repository: SearchHistoryRepository,
) {

    suspend fun execute() = repository.deleteAll()
}