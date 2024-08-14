package com.archrahkshi.moviedatabase.ui

import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.navOptions
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.archrahkshi.moviedatabase.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber.Forest.e

internal val navOptions = navOptions {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}

internal fun EditText.afterTextChanged(action: (Editable?) -> Unit) =
    addTextChangedListener(afterTextChanged = action)

internal fun ImageView.loadFromPath(url: String, width: Int) {
    Picasso.get().load("${IMAGE_BASE_URL}w$width/$url").into(this)
}

// voteAverage from API is 0..10, but rating in RatingBar is 0..5 stars
internal fun Float.toStars() = this / 2

private fun <T> Response<T>.ifSuccessful(responseBody: (T) -> Unit) =
    if (isSuccessful && body() != null) {
        responseBody(body()!!)
        true
    } else {
        false
    }

internal fun <T> Call<T>.then(action: T.() -> Unit) {
    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.ifSuccessful(action)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                e(t)
            }
        }
    )
}
