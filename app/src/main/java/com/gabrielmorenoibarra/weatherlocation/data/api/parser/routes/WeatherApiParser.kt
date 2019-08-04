package com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes

import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes.WeatherApiManager
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.WeatherObservationPage
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Coordinate
import com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase.toWeatherObservationPage
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.logAndShowError

class WeatherApiParser {

    fun get(coordinate: Coordinate,
            username: String,
            listener: (WeatherObservationPage) -> Unit) {
        val north = coordinate.north
        val south = coordinate.south
        val east = coordinate.east
        val west = coordinate.west
        WeatherApiManager().get(
                north,
                south,
                east,
                west,
                username,
                success = {
                    val page = it.toWeatherObservationPage()
                    listener(page)
                },
                failure = {
                    (R.string.error_loading_temperatures).logAndShowError()
                })
    }
}
