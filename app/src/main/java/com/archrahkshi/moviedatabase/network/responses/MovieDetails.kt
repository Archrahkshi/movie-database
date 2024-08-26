package com.archrahkshi.moviedatabase.network.responses

import com.archrahkshi.moviedatabase.network.Response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.archrahkshi.moviedatabase.data.BelongsToCollection as DataBelongsToCollection
import com.archrahkshi.moviedatabase.data.Genre as DataGenre
import com.archrahkshi.moviedatabase.data.MovieDetails as DataMovieDetails
import com.archrahkshi.moviedatabase.data.ProductionCompany as DataProductionCompany
import com.archrahkshi.moviedatabase.data.ProductionCountry as DataProductionCountry
import com.archrahkshi.moviedatabase.data.SpokenLanguage as DataSpokenLanguage

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
) : Response {
    override fun toViewObject() = DataMovieDetails(
        backdropPath ?: posterPath!!,
        genres.orEmpty().map { it.toViewObject() }.joinToString { it.name },
        overview!!,
        productionCompanies.orEmpty().map { it.toViewObject() }.joinToString { it.name },
        title!!,
        voteAverage,
        releaseDate.orEmpty().substringBefore('-')
    )
}

@Serializable
data class BelongsToCollection(
    val id: Int = 0,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?
) : Response {
    override fun toViewObject() = DataBelongsToCollection
}

@Serializable
data class Genre(val id: Int = 0, val name: String?) : Response {
    override fun toViewObject() = DataGenre(name.orEmpty())
}

@Serializable
data class ProductionCompany(
    val id: Int = 0,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
) : Response {
    override fun toViewObject() =
        DataProductionCompany(name.orEmpty())
}

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1") val iso31661: String?,
    val name: String?
) : Response {
    override fun toViewObject() = DataProductionCountry
}

@Serializable
data class SpokenLanguage(
    val englishName: String?,
    @SerialName("iso_639_1") val iso6391: String?,
    val name: String?
) : Response {
    override fun toViewObject() = DataSpokenLanguage
}
