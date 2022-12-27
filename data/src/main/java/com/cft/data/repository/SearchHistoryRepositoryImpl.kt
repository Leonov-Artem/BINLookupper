package com.cft.data.repository

import com.cft.data.source.local.SearchHistoryDao

class SearchHistoryRepositoryImpl(
    private val dao: SearchHistoryDao,
) : SearchHistoryRepository {

    override fun getBinList() = dao.getBinList()

    override fun getDetailsByBin(bin: String) = dao.getDetailsByBin(bin)

    override suspend fun delete(bin: String) = dao.delete(bin)

    override suspend fun deleteAll() = dao.deleteAll()
}