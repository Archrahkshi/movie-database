package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val adult: Boolean = true,
    val backdropPath: String?,
    val belongsToCollection: BelongsToCollection?,
    val budget: Int = 0,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int = 0,
    val imdbId: String?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float = 0f,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>?,
    val productionCountries: List<ProductionCountry>?,
    val releaseDate: String?,
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean = true,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0
)

@Serializable
data class BelongsToCollection(
    val id: Int = 0,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?
)

@Serializable
data class Genre(val id: Int = 0, val name: String?)

@Serializable
data class ProductionCompany(
    val id: Int = 0,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)

@Serializable
data class ProductionCountry(@SerialName("iso_3166_1") val iso31661: String?, val name: String?)

@Serializable
data class SpokenLanguage(
    val englishName: String?,
    @SerialName("iso_639_1") val iso6391: String?,
    val name: String?
)