package com.archrahkshi.moviedatabase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.archrahkshi.moviedatabase.data.DataObject
import com.archrahkshi.moviedatabase.data.vo.MovieDetails

@Entity
data class Movie(
    val backdropPath: String?,
    @PrimaryKey val id: Int,
    val isFavorite: Boolean,
    val isFeedCache: Boolean,
    val genre: String,
    val overview: String,
    val posterPath: String,
    val studio: String,
    val title: String,
    val voteAverage: Float,
    val year: String
) : DataObject {
    override fun toViewObject() = MovieDetails(
        backdropPath,
        id,
        genre,
        overview,
        posterPath,
        studio,
        title,
        voteAverage,
        year,
        isFavorite,
        isFeedCache
    )
}
