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
    val year: String,
    var isFavorite: Boolean,
    var isFeedCache: Boolean
) : ViewObject {
    private val db = AppDatabase.getInstance(appContext).movieDao()

    private fun toDbEntity() = Movie(
        backdropPath,
        id,
        isFavorite,
        isFeedCache,
        genre,
        overview,
        posterPath,
        studio,
        title,
        voteAverage,
        year
    )

    suspend fun saveToDatabase() {
        db.insert(toDbEntity())
    }

    suspend fun removeFromDatabase() {
        db.delete(toDbEntity())
    }

    suspend fun updateLike() {
        db.updateLike(toDbEntity())
    }
}

object BelongsToCollection : ViewObject

data class Genre(val name: String) : ViewObject

data class ProductionCompany(val name: String) : ViewObject

object ProductionCountry : ViewObject

object SpokenLanguage : ViewObject
