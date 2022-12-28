package com.cft.binlookupper.data.usecase

import com.cft.binlookupper.data.repository.SearchHistoryRepository

class GetBinListUseCase(
    private val repository: SearchHistoryRepository,
) {

    fun execute() = repository.getBinList()
}