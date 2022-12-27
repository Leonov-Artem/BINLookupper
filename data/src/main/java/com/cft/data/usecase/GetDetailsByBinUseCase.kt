package com.cft.data.usecase

import com.cft.data.repository.SearchHistoryRepository

class GetDetailsByBinUseCase(
    private val repository: SearchHistoryRepository,
) {

    fun execute(bin: String) = repository.getDetailsByBin(bin)
}