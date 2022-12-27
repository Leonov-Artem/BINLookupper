package com.cft.data.usecase

import com.cft.data.repository.SearchHistoryRepository

class DeleteSearchHistoryUseCase(
    private val repository: SearchHistoryRepository,
) {

    suspend fun execute() = repository.deleteAll()
}