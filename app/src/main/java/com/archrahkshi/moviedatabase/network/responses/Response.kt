package com.archrahkshi.moviedatabase.network.responses

import com.archrahkshi.moviedatabase.data.ViewObject
import com.archrahkshi.moviedatabase.ui.toStars
import com.archrahkshi.moviedatabase.data.Actor as DataActor
import com.archrahkshi.moviedatabase.data.BelongsToCollection as DataBelongsToCollection
import com.archrahkshi.moviedatabase.data.CrewMember as DataCrewMember
import com.archrahkshi.moviedatabase.data.Dates as DataDates
import com.archrahkshi.moviedatabase.data.Genre as DataGenre
import com.archrahkshi.moviedatabase.data.Movie as DataMovie
import com.archrahkshi.moviedatabase.data.MovieCredits as DataMovieCredits
import com.archrahkshi.moviedatabase.data.MovieDetails as DataMovieDetails
import com.archrahkshi.moviedatabase.data.Movies as DataMovies
import com.archrahkshi.moviedatabase.data.ProductionCompany as DataProductionCompany
import com.archrahkshi.moviedatabase.data.ProductionCountry as DataProductionCountry
import com.archrahkshi.moviedatabase.data.SpokenLanguage as DataSpokenLanguage
import com.archrahkshi.moviedatabase.data.TvShow as DataTvShow
import com.archrahkshi.moviedatabase.data.TvShows as DataTvShows

sealed interface Response {
    fun toViewObject(): ViewObject = when (this) { // TODO generate with reflect?
        // MovieCredits.kt
        is MovieCredits -> DataMovieCredits(
            cast.orEmpty().filter { it.name != null }.map { it.toViewObject() as DataActor }
        )
        is Actor -> DataActor(name!!, profilePath)
        is CrewMember -> DataCrewMember
        // MovieDetails.kt
        is MovieDetails -> DataMovieDetails(
            backdropPath ?: posterPath!!,
            genres.orEmpty().map { it.toViewObject() as DataGenre }.joinToString { it.name },
            overview!!,
            productionCompanies.orEmpty().map {
                it.toViewObject() as DataProductionCompany
            }.joinToString { it.name },
            title!!,
            voteAverage,
            releaseDate.orEmpty().substringBefore('-')
        )
        is BelongsToCollection -> DataBelongsToCollection
        is Genre -> DataGenre(name.orEmpty())
        is ProductionCompany -> DataProductionCompany(name.orEmpty())
        is ProductionCountry -> DataProductionCountry
        is SpokenLanguage -> DataSpokenLanguage
        // Movies.kt
        is Movies -> DataMovies(
            results!!.filter {
                it.overview != null && it.posterPath != null && it.title != null && it.voteAverage != 0f
            }.map { it.toViewObject() as DataMovie }
        )
        is Dates -> DataDates
        is Movie ->
            DataMovie(backdropPath, id, overview!!, posterPath!!, title!!, voteAverage.toStars())
        // TvShows.kt
        is TvShows -> DataTvShows(
            results!!.filter {
                it.id != 0 && it.name != null && it.posterPath != null && it.voteAverage != 0f
            }.map { it.toViewObject() as DataTvShow }
        )
        is TvShow -> DataTvShow(id, name!!, posterPath!!, voteAverage.toStars())
    }
}
