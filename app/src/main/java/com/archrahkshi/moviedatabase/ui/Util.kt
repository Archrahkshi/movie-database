package com.archrahkshi.moviedatabase.ui

import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.navOptions
import com.archrahkshi.moviedatabase.R
import com.squareup.picasso.Picasso

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

internal fun ImageView.loadFromUrl(url: String) {
    Picasso.get().load(url).into(this)
}
