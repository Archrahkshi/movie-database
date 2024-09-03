package com.archrahkshi.moviedatabase.data.network

import com.archrahkshi.moviedatabase.data.vo.ViewObject

interface Response {
    fun toViewObject(): ViewObject
}

// voteAverage from API is 0..10, but rating in RatingBar is 0..5 stars
fun Float.toStars() = this / 2
