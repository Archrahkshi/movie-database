package com.archrahkshi.moviedatabase.network

import com.archrahkshi.moviedatabase.BuildConfig.API_KEY
import com.archrahkshi.moviedatabase.BuildConfig.BASE_URL
import com.archrahkshi.moviedatabase.BuildConfig.DEBUG
import com.archrahkshi.moviedatabase.data.MovieCredits
import com.archrahkshi.moviedatabase.data.MovieDetails
import com.archrahkshi.moviedatabase.data.Movies
import com.archrahkshi.moviedatabase.data.TvShows
import com.archrahkshi.moviedatabase.ui.getDefaultCountry
import com.archrahkshi.moviedatabase.ui.getDefaultLanguage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy.Builtins.SnakeCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@OptIn(ExperimentalSerializationApi::class)
private val format = Json { namingStrategy = SnakeCase }
val apiClient by lazy {
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

interface ApiInterface {
    @GET("movie/{movie_list}")
    fun getMovies(
        @Path("movie_list") movieList: String,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("page") page: Int = 1,
        @Query("region") region: String = getDefaultCountry(),
        @Query("api_key") apiKey: String = API_KEY
    ): Call<Movies>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String? = null,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieDetails>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieCredits>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String = getDefaultLanguage(),
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<TvShows>
}
