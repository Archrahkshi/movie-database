package com.archrahkshi.moviedatabase.data.vo

data class Movies(val results: List<Movie>) : ViewObject

object Dates : ViewObject

data class Movie(
    val backdropPath: String?,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val ratingInStars: Float,
    val title: String,
    val voteAverage: String,
    val year: String
) : ViewObject
