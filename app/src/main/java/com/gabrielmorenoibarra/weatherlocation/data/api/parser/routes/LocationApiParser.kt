package com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes

import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes.LocationApiManager
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.GeoNamePage
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.LocationPage
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Location
import com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase.toGeoNamePage
import com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase.toLocationPage
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.logAndShowError

class LocationApiParser {

    fun getLocation(location: Location,
                    nPage: Int,
                    nItems: Int,
                    listener: (GeoNamePage) -> Unit) {
        val name = location.name
        val language = location.language
        val nameRequired = location.nameRequired
        val style = location.style
        val username = location.username
        LocationApiManager().getLocation(
                name,
                language,
                nameRequired,
                style,
                username,
                nPage,
                nItems,
                success = {
                    val page = it.toGeoNamePage()
                    listener(page)
                },
                failure = {
                    (R.string.error_loading_locations).logAndShowError()
                })
    }
}
