package com.gabrielmorenoibarra.weatherlocation.framework.project.util

import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.weatherlocation.App
import com.gabrielmorenoibarra.weatherlocation.BuildConfig
import org.jetbrains.anko.longToast

fun Exception?.log() {
    this?.printStackTrace()
}

fun String.logError() {
    if (BuildConfig.DEBUG) {
        App.instance.longToast(this)
        KLog.e(this)
    }
}
