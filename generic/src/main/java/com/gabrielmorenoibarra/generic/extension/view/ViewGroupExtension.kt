package com.gabrielmorenoibarra.generic.extension.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, false)
}

fun ViewGroup.inflateCustom(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, true)
}
