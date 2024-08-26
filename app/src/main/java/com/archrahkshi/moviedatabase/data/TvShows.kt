package com.archrahkshi.moviedatabase.data

data class TvShows(val results: List<TvShow>) : ViewObject

data class TvShow(
    val id: Int,
    val name: String,
    val posterPath: String,
    val ratingInStars: Float
) : ViewObject
