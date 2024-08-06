package ru.androidschool.intensiv.data

class TvShow(
    var title: String? = "",
    var voteAverage: Float = 0f
) {
    val rating: Float
        get() = voteAverage.div(2)
}
