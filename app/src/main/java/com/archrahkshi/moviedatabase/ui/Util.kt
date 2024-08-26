package com.archrahkshi.moviedatabase.ui

import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

fun EditText.afterTextChanged(action: (Editable?) -> Unit) =
    addTextChangedListener(afterTextChanged = action)

fun ImageView.loadFromPath(path: String, width: Int) {
    Picasso.get().load("${IMAGE_BASE_URL}w$width$path").into(this)
}
