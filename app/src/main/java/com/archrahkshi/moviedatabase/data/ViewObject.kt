package com.archrahkshi.moviedatabase.data

import com.archrahkshi.moviedatabase.network.responses.Response
import java.util.Locale
import com.archrahkshi.moviedatabase.network.responses.Actor as ResponseActor
import com.archrahkshi.moviedatabase.network.responses.BelongsToCollection as ResponseBelongsToCollection
import com.archrahkshi.moviedatabase.network.responses.CrewMember as ResponseCrewMember
import com.archrahkshi.moviedatabase.network.responses.Dates as ResponseDates
import com.archrahkshi.moviedatabase.network.responses.Genre as ResponseGenre
import com.archrahkshi.moviedatabase.network.responses.Movie as ResponseMovie
import com.archrahkshi.moviedatabase.network.responses.MovieCredits as ResponseMovieCredits
import com.archrahkshi.moviedatabase.network.responses.MovieDetails as ResponseMovieDetails
import com.archrahkshi.moviedatabase.network.responses.Movies as ResponseMovies
import com.archrahkshi.moviedatabase.network.responses.ProductionCompany as ResponseProductionCompany
import com.archrahkshi.moviedatabase.network.responses.ProductionCountry as ResponseProductionCountry
import com.archrahkshi.moviedatabase.network.responses.SpokenLanguage as ResponseSpokenLanguage
import com.archrahkshi.moviedatabase.network.responses.TvShow as ResponseTvShow
import com.archrahkshi.moviedatabase.network.responses.TvShows as ResponseTvShows

interface ViewObject

fun Response.toViewObject(): ViewObject = when (this) { // TODO generate with reflect?
    // MovieCredits.kt
    is ResponseMovieCredits -> MovieCredits(
        cast.orEmpty().filter { it.name != null }.map { it.toViewObject() as Actor }
    )
    is ResponseActor -> Actor(name!!, profilePath)
    is ResponseCrewMember -> CrewMember
    // MovieDetails.kt
    is ResponseMovieDetails -> MovieDetails(
        backdropPath ?: posterPath!!,
        genres.orEmpty().map { it.toViewObject() as Genre }.joinToString { it.name },
        overview!!,
        productionCompanies.orEmpty().map {
            it.toViewObject() as ProductionCompany
        }.joinToString { it.name },
        title!!,
        voteAverage,
        releaseDate.orEmpty().substringBefore('-')
    )
    is ResponseBelongsToCollection -> BelongsToCollection
    is ResponseGenre -> Genre(name.orEmpty())
    is ResponseProductionCompany -> ProductionCompany(name.orEmpty())
    is ResponseProductionCountry -> ProductionCountry
    is ResponseSpokenLanguage -> SpokenLanguage
    // Movies.kt
    is ResponseMovies -> Movies(
        results!!.filter {
            it.overview != null &&
                    it.posterPath != null &&
                    it.title != null &&
                    it.voteAverage != 0f &&
                    it.releaseDate != null
        }.map { it.toViewObject() as Movie }
    )
    is ResponseDates -> Dates
    is ResponseMovie -> Movie(
        backdropPath,
        id,
        overview!!,
        posterPath!!,
        voteAverage.toStars(),
        title!!,
        String.format(Locale.getDefault(), "%.1f", voteAverage),
        releaseDate!!.substringBefore('-')
    )
    // TvShows.kt
    is ResponseTvShows -> TvShows(
        results!!.filter {
            it.id != 0 && it.name != null && it.posterPath != null && it.voteAverage != 0f
        }.map { it.toViewObject() as TvShow }
    )
    is ResponseTvShow -> TvShow(id, name!!, posterPath!!, voteAverage.toStars())
}

// voteAverage from API is 0..10, but rating in RatingBar is 0..5 stars
private fun Float.toStars() = this / 2
