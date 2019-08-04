package com.gabrielmorenoibarra.generic.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.isConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnected ?: false
}
