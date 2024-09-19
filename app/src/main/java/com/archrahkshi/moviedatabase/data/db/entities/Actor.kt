package com.archrahkshi.moviedatabase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.archrahkshi.moviedatabase.data.DataObject
import com.archrahkshi.moviedatabase.data.vo.Actor

@Entity
data class Actor(
    @PrimaryKey val id: Int,
    val name: String,
    val profilePath: String?
) : DataObject {
    override fun toViewObject() = Actor(id, name, profilePath)
}
