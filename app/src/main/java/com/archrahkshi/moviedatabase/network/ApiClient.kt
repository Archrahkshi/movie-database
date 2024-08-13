package com.archrahkshi.moviedatabase.network

import com.archrahkshi.moviedatabase.BuildConfig.API_KEY
import com.archrahkshi.moviedatabase.BuildConfig.BASE_URL
import com.archrahkshi.moviedatabase.BuildConfig.DEBUG
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy.Builtins.SnakeCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.create

object ApiClient {
    @OptIn(ExperimentalSerializationApi::class)
    private val format = Json { namingStrategy = SnakeCase }
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
            format.asConverterFactory("application/json".toMediaType())
        ).build().create<ApiInterface>()
    }

    fun getNowPlayingMovies(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getNowPlayingMovies(API_KEY, language, page, region)

    fun getPopularMovies(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getPopularMovies(API_KEY, language, page, region)

    fun getTopRatedMovies(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getTopRatedMovies(API_KEY, language, page, region)

    fun getUpcomingMovies(language: String = "ru-RU", page: Int = 1, region: String = "RU") =
        apiClient.getUpcomingMovies(API_KEY, language, page, region)

    fun getPopularTvShows(language: String = "ru-RU", page: Int = 1) =
        apiClient.getPopularTvShows(API_KEY, language, page)
}
