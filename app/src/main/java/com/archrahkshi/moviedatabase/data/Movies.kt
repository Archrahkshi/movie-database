package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val dates: Dates? = null,
    val page: Int = 0,
    val results: List<Movie> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

@Serializable
data class Dates(val maximum: String, val minimum: String)
