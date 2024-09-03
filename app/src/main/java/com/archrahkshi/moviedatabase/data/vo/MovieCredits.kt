package com.archrahkshi.moviedatabase.data.vo

data class MovieCredits(val cast: List<Actor>) : ViewObject

data class Actor(val id: Int = 0, val name: String, val profilePath: String?) : ViewObject

object CrewMember : ViewObject
