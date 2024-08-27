package com.archrahkshi.moviedatabase.network.responses

import com.archrahkshi.moviedatabase.network.Response
import kotlinx.serialization.Serializable
import com.archrahkshi.moviedatabase.data.Actor as DataActor
import com.archrahkshi.moviedatabase.data.CrewMember as DataCrewMember
import com.archrahkshi.moviedatabase.data.MovieCredits as DataMovieCredits

@Serializable
data class MovieCredits(
    val id: Int = 0,
    val cast: List<Actor>?,
    val crew: List<CrewMember>?
) : Response {
    override fun toViewObject() = DataMovieCredits(
        cast.orEmpty().filter { it.name != null }.map { it.toViewObject() }
    )
}

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
) : Response {
    override fun toViewObject() = DataActor(id, name!!, profilePath)
}

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
) : Response {
    override fun toViewObject() = DataCrewMember
}
