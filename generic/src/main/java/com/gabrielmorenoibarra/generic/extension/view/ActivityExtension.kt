package com.gabrielmorenoibarra.generic.extension.view

import android.app.Activity
import android.view.View
import com.gabrielmorenoibarra.g.GShowHideKeyboard
import com.gabrielmorenoibarra.generic.extension.hideKeyboard

fun Activity.hideKeyboard(showHideKeyboard: GShowHideKeyboard) {
    window.decorView.rootView.setOnTouchListener { _, _ ->
        showHideKeyboard.hide()
        return@setOnTouchListener false
    }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}
