package com.gabrielmorenoibarra.generic.extension.view

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.initVerticalLayout(@DrawableRes drawableRes: Int) {
    val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    ContextCompat.getDrawable(context, drawableRes)?.let { itemDecorator.setDrawable(it) }
    addItemDecoration(itemDecorator)
    initLinearLayout(RecyclerView.VERTICAL)
}

fun RecyclerView.initLinearLayout(orientation: Int, hasFixedSize: Boolean = true) {
    setHasFixedSize(hasFixedSize)
    layoutManager = LinearLayoutManager(context, orientation, false)
}
