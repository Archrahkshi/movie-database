package com.archrahkshi.moviedatabase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShow(
    @PrimaryKey val id: Int,
    val name: String,
    val posterPath: String,
    val ratingInStars: Float
)
