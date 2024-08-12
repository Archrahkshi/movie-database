package com.archrahkshi.moviedatabase.network

import com.archrahkshi.moviedatabase.BuildConfig.API_KEY
import com.archrahkshi.moviedatabase.BuildConfig.BASE_URL
import com.archrahkshi.moviedatabase.BuildConfig.DEBUG
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.create

object MovieApiClient {
    private val apiClient by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(
            if (DEBUG) {
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
                    .build()
            } else {
                OkHttpClient()
            }
        ).addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType())
        ).build().create<MovieApiInterface>()
    }

    fun getNowPlaying(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getNowPlaying(API_KEY, language, page, region)

    fun getPopular(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getPopular(API_KEY, language, page, region)

    fun getTopRated(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getTopRated(API_KEY, language, page, region)

    fun getUpcoming(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getUpcoming(API_KEY, language, page, region)
}
