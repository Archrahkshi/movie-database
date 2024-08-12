package com.archrahkshi.moviedatabase.data

object MockRepository {

    fun getTvShows() = (1..10).map {
        TvShow(
            "TV show $it",
            10f - it,
            "https://i.ytimg.com/vi/mkD5Nsr4vfc/maxresdefault.jpg"
        )
    }
}
