package com.gabrielmorenoibarra.weatherlocation.data.util

import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.weatherlocation.BuildConfig
import com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes.LocationApiParser
import com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes.WeatherApiParser
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Coordinate
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.request.Location

class ManualTest {

    init {
//        check()
    }
}

private fun check() {
    val name = "Madrid"
    val location = Location(name)
    LocationApiParser().get(location,
            BuildConfig.USERNAME_IL_GEONAMES_SAMPLE,
            0, 20) {
        KLog.d("$it")
    }

    val north = 44.1
    val south = -9.9
    val east = -22.4
    val west = 55.2
    val accuracyLevel = 10
    val coordinate = Coordinate(north, south, east, west, accuracyLevel)
    WeatherApiParser().get(
            coordinate,
            BuildConfig.USERNAME_IL_GEONAMES_SAMPLE,
            0, 20) {
        KLog.d("$it")
    }
}
