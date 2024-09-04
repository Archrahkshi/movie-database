package com.archrahkshi.moviedatabase.data.network.responses

import com.archrahkshi.moviedatabase.data.network.Response
import kotlinx.serialization.Serializable
import com.archrahkshi.moviedatabase.data.vo.TvShow as DataTvShow
import com.archrahkshi.moviedatabase.data.vo.TvShows as DataTvShows

@Serializable
data class TvShows(
    val page: Int = 0,
    val results: List<TvShow>?,
    val totalPages: Int = 0,
    val totalResults: Int = 0
) : Response {
    override fun toViewObject() = DataTvShows(
        results!!.filter {
            it.id != 0 && it.name != null && it.posterPath != null && it.voteAverage != 0f
        }.map { it.toViewObject() }
    )
}

@Serializable
data class TvShow(
    val adult: Boolean?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    val id: Int = 0,
    val name: String?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Float = 0f,
    val posterPath: String?,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0
) : Response {
    override fun toViewObject() = DataTvShow(id, name!!, posterPath!!, voteAverage.toStars())
}
