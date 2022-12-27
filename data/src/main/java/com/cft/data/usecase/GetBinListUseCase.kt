package com.cft.data.usecase

import com.cft.data.repository.SearchHistoryRepository

class GetBinListUseCase(
    private val repository: SearchHistoryRepository,
) {

    fun execute() = repository.getBinList()
}