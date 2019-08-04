package com.gabrielmorenoibarra.weatherlocation.data.util

import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes.LocationApiParser
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Location

class ManualTest {

    init {
//        check()
    }
}

private fun check() {
    val name = "Madrid"
    val location = Location(name)
    LocationApiParser().getLocation(location, 0, 20) {
        KLog.d("$it")
    }
}
