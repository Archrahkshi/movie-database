package com.archrahkshi.moviedatabase

import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.navOptions
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.io
import timber.log.Timber.Forest.e
import java.util.Locale

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

fun <T : Any> Single<T>.then(
    subscribeScheduler: Scheduler = io(),
    observeScheduler: Scheduler = mainThread(),
    action: T.() -> Unit
) = subscribeOn(subscribeScheduler).observeOn(observeScheduler).subscribe(action, ::e)

fun getDefaultLanguage(): String = Locale.getDefault().toLanguageTag()

fun getDefaultCountry(): String = Locale.getDefault().country
