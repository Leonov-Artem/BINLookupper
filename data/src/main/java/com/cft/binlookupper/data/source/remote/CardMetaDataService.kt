package com.cft.binlookupper.data.source.remote

import com.cft.binlookupper.data.model.CardMetaData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CardMetaDataService {

    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun lookupByBin(
        @Path("bin") bin: String,
    ): Response<CardMetaData>
}