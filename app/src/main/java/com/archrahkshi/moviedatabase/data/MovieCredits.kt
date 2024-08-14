package com.archrahkshi.moviedatabase.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieCredits(val id: Int, val cast: List<Actor>, val crew: List<CrewMember>)

@Serializable
data class Actor(
    val adult: Boolean = true,
    val castId: Int = 0,
    val character: String?,
    val creditId: String?,
    val gender: Int = 0,
    val id: Int = 0,
    val knownForDepartment: String?,
    val name: String?,
    val order: Int = 0,
    val originalName: String?,
    val popularity: Float = 0f,
    val profilePath: String?
)

@Serializable
data class CrewMember(
    val adult: Boolean = true,
    val creditId: String?,
    val department: String?,
    val gender: Int = 0,
    val id: Int = 0,
    val job: String?,
    val knownForDepartment: String?,
    val name: String?,
    val originalName: String?,
    val popularity: Float = 0f,
    val profilePath: String?
)
