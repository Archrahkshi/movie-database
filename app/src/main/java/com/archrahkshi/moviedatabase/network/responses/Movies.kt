package com.archrahkshi.moviedatabase.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val dates: Dates? = null,
    val page: Int = 0,
    val results: List<Movie>?,
    val totalPages: Int = 0,
    val totalResults: Int = 0
) : Response

@Serializable
data class Dates(val maximum: String, val minimum: String) : Response

@Serializable
data class Movie(
    val adult: Boolean = true,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int = 0,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float = 0f,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean = true,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0
) : Response
