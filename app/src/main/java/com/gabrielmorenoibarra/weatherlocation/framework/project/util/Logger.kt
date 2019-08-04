package com.gabrielmorenoibarra.weatherlocation.framework.project.util

import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.weatherlocation.App
import com.gabrielmorenoibarra.weatherlocation.BuildConfig
import org.jetbrains.anko.longToast

fun Exception?.log() {
    this?.printStackTrace()
}

fun Int.logAndShowError() {
    val app = App.instance
    val s = app.getString(this)
    app.longToast(s)
    KLog.e(s)
}

fun String.logError() {
    if (BuildConfig.DEBUG) {
        App.instance.longToast(this)
        KLog.e(this)
    }
}
