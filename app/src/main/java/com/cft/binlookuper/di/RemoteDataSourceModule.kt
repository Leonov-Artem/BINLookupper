package com.cft.binlookuper.di

import com.cft.binlookuper.BuildConfig
import com.cft.data.source.remote.CardMetaDataService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<CardMetaDataService> {
        get<Retrofit>().create(CardMetaDataService::class.java)
    }
}