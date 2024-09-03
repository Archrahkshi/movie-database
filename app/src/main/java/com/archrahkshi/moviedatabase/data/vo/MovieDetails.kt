package com.archrahkshi.moviedatabase.data.vo

import com.archrahkshi.moviedatabase.MovieDatabaseApp.Companion.appContext
import com.archrahkshi.moviedatabase.data.db.AppDatabase
import com.archrahkshi.moviedatabase.data.db.entities.Movie

data class MovieDetails(
    val backdropPath: String?,
    val id: Int,
    val genre: String,
    val overview: String,
    val posterPath: String,
    val studio: String,
    val title: String,
    val voteAverage: Float,
    val year: String
) : ViewObject {
    override fun saveToDatabase() {
        AppDatabase.getInstance(appContext).movieDao().insert(
            Movie(
                backdropPath,
                id,
                genre,
                overview,
                posterPath,
                studio,
                title,
                voteAverage,
                year
            )
        )
    }
}

object BelongsToCollection : ViewObject

data class Genre(val name: String) : ViewObject

data class ProductionCompany(val name: String) : ViewObject

object ProductionCountry : ViewObject

object SpokenLanguage : ViewObject
