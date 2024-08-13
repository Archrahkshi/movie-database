package com.archrahkshi.moviedatabase.ui

import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import com.squareup.picasso.Picasso

fun EditText.afterTextChanged(action: (Editable?) -> Unit) =
    addTextChangedListener(afterTextChanged = action)

fun ImageView.loadFromUrl(url: String) {
    Picasso.get().load(url).into(this)
}
