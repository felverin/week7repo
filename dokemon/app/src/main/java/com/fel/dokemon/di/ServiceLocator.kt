package com.fel.dokemon.di

import com.fel.dokemon.BuildConfig
import com.fel.dokemon.data.remote.ApiKeyInterceptor
import com.fel.dokemon.data.remote.DokemonApi
import com.fel.dokemon.data.repository.DokemonRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object ServiceLocator {

    private const val BASE_URL = "https://api.pokemontcg.io/v2/"

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.DOKEMON_API_KEY))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
                else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    private val api: DokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DokemonApi::class.java)
    }

    val repository: DokemonRepository by lazy { DokemonRepository(api) }
}
