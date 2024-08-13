package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val adult: Boolean = true,
    val backdropPath: String,
    val belongsToCollection: String,
    val budget: Int = 0,
    val genres: Map<Int, String>,
    val homepage: String,
    val id: Int = 0,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float = 0f,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: Map<String, String>,
    val releaseDate: String,
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0
)

@Serializable
data class ProductionCompany(
    val id: Int = 0,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

@Serializable
data class SpokenLanguage(val englishName: String, val iso6391: String, val name: String)