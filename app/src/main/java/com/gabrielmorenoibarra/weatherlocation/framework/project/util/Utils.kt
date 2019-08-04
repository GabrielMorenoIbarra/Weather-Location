package com.gabrielmorenoibarra.weatherlocation.framework.project.util

import com.gabrielmorenoibarra.g.G
import com.gabrielmorenoibarra.generic.extension.isConnected
import com.gabrielmorenoibarra.weatherlocation.App
import okhttp3.Request

fun Request.Builder.addCache() {
    if (App.instance.isConnected()) {
        addHeader("Cache-Control", "public, max-age=${10}")
    } else {
        addHeader("Cache-Control", "public, only-if-cached, max-stale=${7 * G.DAY_IN_MILLIS / 1000}")
    }
}