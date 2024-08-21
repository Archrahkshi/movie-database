package com.archrahkshi.moviedatabase.data

data class MovieCredits(val cast: List<Actor>) : ViewObject

data class Actor(val name: String, val profilePath: String?) : ViewObject

object CrewMember : ViewObject
