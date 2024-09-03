package com.archrahkshi.moviedatabase.data.vo

import com.archrahkshi.moviedatabase.data.network.apiClient
import io.reactivex.rxjava3.schedulers.Schedulers.io

data class Movies(val results: List<Movie>) : ViewObject

object Dates : ViewObject

data class Movie(
    val backdropPath: String?,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val ratingInStars: Float,
    val title: String,
    val voteAverage: String,
    val year: String
) : ViewObject {
    override fun saveToDatabase() {
        apiClient.getMovieDetails(id).subscribeOn(io()).subscribe { movieDetails ->
            movieDetails.toViewObject().saveToDatabase()
        }.dispose()
    }
}
