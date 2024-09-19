package com.archrahkshi.moviedatabase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.archrahkshi.moviedatabase.data.DataObject
import com.archrahkshi.moviedatabase.data.vo.TvShow

@Entity
data class TvShow(
    @PrimaryKey val id: Int,
    val name: String,
    val posterPath: String,
    val ratingInStars: Float
) : DataObject {
    override fun toViewObject() = TvShow(id, name, posterPath, ratingInStars)
}
