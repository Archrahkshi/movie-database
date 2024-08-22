package com.archrahkshi.moviedatabase.network

import com.archrahkshi.moviedatabase.BuildConfig.API_KEY
import com.archrahkshi.moviedatabase.BuildConfig.BASE_URL
import com.archrahkshi.moviedatabase.BuildConfig.DEBUG
import com.archrahkshi.moviedatabase.network.responses.MovieCredits
import com.archrahkshi.moviedatabase.network.responses.MovieDetails
import com.archrahkshi.moviedatabase.network.responses.Movies
import com.archrahkshi.moviedatabase.network.responses.TvShows
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy.Builtins.SnakeCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

private fun getDefaultLanguage(): String = Locale.getDefault().toLanguageTag()

private fun getDefaultCountry(): String = Locale.getDefault().country

@OptIn(ExperimentalSerializationApi::class)
val apiClient by lazy {
    Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(
            OkHttpClient.Builder().apply {
                if (DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
                }
            }.build()
        )
        val json = Json { namingStrategy = SnakeCase }
        addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }.build().create<ApiInterface>()
}

interface ApiInterface {
    @GET("movie/{movie_list}")
    fun getMovies(
        @Path("movie_list") movieList: String,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("page") page: Int = 1,
        @Query("region") region: String = getDefaultCountry(),
        @Query("api_key") apiKey: String = API_KEY
    ): Single<Movies>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String? = null,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("api_key") apiKey: String = API_KEY
    ): Single<MovieDetails>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("api_key") apiKey: String = API_KEY
    ): Single<MovieCredits>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String = getDefaultLanguage(),
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): Single<TvShows>

    @GET("search/movie")
    fun searchForMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = getDefaultLanguage(),
        @Query("primary_release_year") primaryReleaseYear: String? = null,
        @Query("page") page: Int = 1,
        @Query("region") region: String = getDefaultCountry(),
        @Query("year") year: String? = null,
        @Query("api_key") apiKey: String = API_KEY
    ): Single<Movies>
}
