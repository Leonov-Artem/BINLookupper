package com.cft.binlookupper.data.usecase

import com.cft.binlookupper.data.repository.SearchHistoryRepository

class GetDetailsByBinUseCase(
    private val repository: SearchHistoryRepository,
) {

    fun execute(bin: String) = repository.getDetailsByBin(bin)
}