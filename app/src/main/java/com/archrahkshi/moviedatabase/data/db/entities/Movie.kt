package com.archrahkshi.moviedatabase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    val backdropPath: String?,
    @PrimaryKey val id: Int,
    val genre: String,
    val overview: String,
    val posterPath: String,
    val studio: String,
    val title: String,
    val voteAverage: Float,
    val year: String
)
