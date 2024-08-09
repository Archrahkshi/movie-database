package com.archrahkshi.moviedatabase.data

object MockRepository {

    fun getMovies(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        for (x in 0..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x
            )
            moviesList.add(movie)
        }

        return moviesList
    }

    fun getTvShows() = (1..10).map {
        TvShow(
            "TV show $it",
            10f - it,
            "https://i.ytimg.com/vi/mkD5Nsr4vfc/maxresdefault.jpg"
        )
    }
}
