package com.gabrielmorenoibarra.weatherlocation

import androidx.multidex.MultiDexApplication
import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.weatherlocation.data.util.ManualTest

class App : MultiDexApplication() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        KLog.launch(BuildConfig.DEBUG)

        if (BuildConfig.DEBUG) ManualTest()
    }
}
