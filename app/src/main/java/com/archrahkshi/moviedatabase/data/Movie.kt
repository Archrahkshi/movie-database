package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val adult: Boolean = true,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int?,
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
)
