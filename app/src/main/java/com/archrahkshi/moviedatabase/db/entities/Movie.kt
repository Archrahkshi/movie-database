package com.archrahkshi.moviedatabase.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val backdropPath: String?,
    val genre: String,
    val overview: String,
    val posterPath: String,
    val studio: String,
    val title: String,
    val voteAverage: String,
    val year: String
)
