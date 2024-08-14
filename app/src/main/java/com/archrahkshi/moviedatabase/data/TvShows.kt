package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Serializable
data class TvShows(
    val page: Int = 0,
    val results: List<TvShow>?,
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
