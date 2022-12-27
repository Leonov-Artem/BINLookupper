package com.cft.data.usecase

import com.cft.data.repository.CardMetaDataRepository

class LookupByBinUseCase(
    private val repository: CardMetaDataRepository,
) {

    fun execute(bin: String?) = repository.lookupByBin(bin)
}