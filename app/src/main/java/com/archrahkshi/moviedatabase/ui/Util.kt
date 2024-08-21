package com.archrahkshi.moviedatabase.ui

import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.navOptions
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.archrahkshi.moviedatabase.R
import com.squareup.picasso.Picasso

val navOptions = navOptions {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}

fun EditText.afterTextChanged(action: (Editable?) -> Unit) =
    addTextChangedListener(afterTextChanged = action)

fun ImageView.loadFromPath(path: String, width: Int) {
    Picasso.get().load("${IMAGE_BASE_URL}w$width$path").into(this)
}

// voteAverage from API is 0..10, but rating in RatingBar is 0..5 stars
fun Float.toStars() = this / 2
