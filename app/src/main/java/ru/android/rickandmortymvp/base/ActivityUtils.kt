package ru.android.rickandmortymvp.base

import android.app.Activity
import android.view.View

fun Activity.hideSoftwareKeyboard(delay: Long = 300L, action: (() -> Unit)? = null) {
    val view = findViewById<View>(android.R.id.content)

    getInputMethodManager()
        .hideSoftInputFromWindow(view.windowToken, 0)

    action?.let { view?.postDelayed(it, delay) }
}
