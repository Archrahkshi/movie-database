package com.archrahkshi.moviedatabase.data.network.responses

import com.archrahkshi.moviedatabase.data.network.Response
import com.archrahkshi.moviedatabase.data.network.toStars
import kotlinx.serialization.Serializable
import java.util.Locale
import com.archrahkshi.moviedatabase.data.vo.Dates as DataDates
import com.archrahkshi.moviedatabase.data.vo.Movie as DataMovie
import com.archrahkshi.moviedatabase.data.vo.Movies as DataMovies

@Serializable
data class Movies(
    val dates: Dates? = null,
    val page: Int = 0,
    val results: List<Movie>?,
    val totalPages: Int = 0,
    val totalResults: Int = 0
) : Response {
    override fun toViewObject() = DataMovies(
        results!!.filter {
            it.overview != null &&
                    it.posterPath != null &&
                    it.title != null &&
                    it.voteAverage != 0f &&
                    it.releaseDate != null
        }.map { it.toViewObject() }
    )
}

@Serializable
data class Dates(val maximum: String, val minimum: String) : Response {
    override fun toViewObject() = DataDates
}

@Serializable
data class Movie(
    val adult: Boolean = true,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int = 0,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float = 0f,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean = true,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0
) : Response {
    override fun toViewObject() = DataMovie(
        backdropPath,
        id,
        overview!!,
        posterPath!!,
        voteAverage.toStars(),
        title!!,
        String.format(Locale.getDefault(), "%.1f", voteAverage),
        releaseDate!!.substringBefore('-')
    )
}
