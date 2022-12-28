package com.cft.binlookupper.data.usecase

import com.cft.binlookupper.data.repository.CardMetaDataRepository

class LookupByBinUseCase(
    private val repository: CardMetaDataRepository,
) {

    fun execute(bin: String?) = repository.lookupByBin(bin)
}