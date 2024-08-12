package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("ArrayInDataClass")
@Serializable
data class Movie(
    @SerialName("adult") val isAdult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("genre_ids") val genreIds: Array<Int>,
    val id: Int,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Float = 0f,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("release_date") val releaseDate: String,
    val title: String,
    @SerialName("video") val isVideo: Boolean = true,
    @SerialName("vote_average") val voteAverage: Float = 0f,
    @SerialName("vote_count") val voteCount: Int = 0
)
