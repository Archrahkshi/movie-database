package com.archrahkshi.moviedatabase.data

data class MovieDetails(
    val background: String,
    val genre: String,
    val overview: String,
    val studio: String,
    val title: String,
    val voteAverage: Float,
    val year: String
) : ViewObject

object BelongsToCollection : ViewObject

data class Genre(val name: String) : ViewObject

data class ProductionCompany(val name: String) : ViewObject

object ProductionCountry : ViewObject

object SpokenLanguage : ViewObject
