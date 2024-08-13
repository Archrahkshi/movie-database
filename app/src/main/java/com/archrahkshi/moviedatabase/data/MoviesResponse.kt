package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Suppress("ArrayInDataClass")
@Serializable
data class MoviesResponse(
    val dates: Pair<String, String>? = null,
    val page: Int = 0,
    val results: Array<Movie> = emptyArray(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
