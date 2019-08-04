package com.gabrielmorenoibarra.generic.extension.view

import android.view.View

fun View.setVisible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.fadeIn() {
    visible()
    animate().alpha(1f).withEndAction { visible() }
}

fun View.fadeOut() {
    animate().alpha(0f).withEndAction { gone() }
}

fun View.enable() {
    isEnabled = true
}

fun View.enableAlphaPerform() {
    enable()
    alpha = 1f
}

fun View.disable() {
    isEnabled = false
}

fun View.disableAlphaPerform(alpha: Float = 0.2f) {
    disable()
    this.alpha = 0.2f
}

fun View.select() {
    isSelected = true
}

fun View.deselect() {
    isSelected = false
}

fun View.showNoResultsPerform(nPage: Int, vararg lists: List<*>): Boolean {
    lists.iterator().forEach {
        if (it.isNotEmpty()) {
            gone()
            return false
        }
    }
    if (nPage == 1) {
        visible()
    }
    return true
}
