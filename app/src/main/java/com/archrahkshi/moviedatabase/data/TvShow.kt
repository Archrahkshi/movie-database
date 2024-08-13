package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Serializable
data class TvShow(
    val adult: Boolean,
    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val id: Int = 0,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Float = 0f,
    val posterPath: String,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0
)
