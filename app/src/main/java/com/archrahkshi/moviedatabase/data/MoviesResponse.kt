package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Suppress("ArrayInDataClass")
@Serializable
data class MoviesResponse(
    val page: Int = 0,
    val results: Array<Movie> = emptyArray(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
