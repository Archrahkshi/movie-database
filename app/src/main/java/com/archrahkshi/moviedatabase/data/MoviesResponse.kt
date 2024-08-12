package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("ArrayInDataClass")
@Serializable
data class MoviesResponse(
    val dates: Pair<String, String>? = null,
    val page: Int = 0,
    val results: Array<Movie> = emptyArray(),
    @SerialName("total_pages") val totalPages: Int = 0,
    @SerialName("total_results") val totalResults: Int = 0
)
