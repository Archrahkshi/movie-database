package com.archrahkshi.moviedatabase.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Actor(
    @PrimaryKey val id: Int,
    val name: String,
    val profilePath: String?
)
