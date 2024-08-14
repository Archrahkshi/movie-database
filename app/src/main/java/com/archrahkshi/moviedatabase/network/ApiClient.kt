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
import java.util.Locale

object ApiClient {
    @OptIn(ExperimentalSerializationApi::class)
    private val format = Json { namingStrategy = SnakeCase }
    private val apiClient by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(
            OkHttpClient.Builder().apply {
                if (DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
                }
            }.build()
        ).addConverterFactory(
            format.asConverterFactory("application/json".toMediaType())
        ).build().create<ApiInterface>()
    }

    fun getNowPlayingMovies(
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String = Locale.getDefault().country
    ) = apiClient.getNowPlayingMovies(API_KEY, language, page, region)

    fun getPopularMovies(
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String = Locale.getDefault().country
    ) = apiClient.getPopularMovies(API_KEY, language, page, region)

    fun getUpcomingMovies(
        language: String = Locale.getDefault().language,
        page: Int = 1,
        region: String = Locale.getDefault().country
    ) = apiClient.getUpcomingMovies(API_KEY, language, page, region)

    fun getMovieDetails(
        movieId: Int,
        appendToResponse: String? = null,
        language: String = Locale.getDefault().language
    ) = apiClient.getMovieDetails(movieId, API_KEY, appendToResponse, language)

    fun getMovieCredits(movieId: Int, language: String = Locale.getDefault().language) =
        apiClient.getMovieCredits(movieId, API_KEY, language)

    fun getPopularTvShows(language: String = Locale.getDefault().language, page: Int = 1) =
        apiClient.getPopularTvShows(API_KEY, language, page)
}
