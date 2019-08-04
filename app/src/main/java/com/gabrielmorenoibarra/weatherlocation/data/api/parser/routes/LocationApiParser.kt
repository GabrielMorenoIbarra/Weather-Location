package com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes

import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes.LocationApiManager
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.LocationPage
import com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase.toLocationPage
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.logAndShowError

class LocationApiParser {

    fun getLocation(extended: String, nPage: Int, nItems: Int, listener: (LocationPage) -> Unit) {
        LocationApiManager().getLocation(extended, nPage, nItems,
            success = {
                val page = it.toLocationPage()
                listener(page)
            },
            failure = { (R.string.error_loading_location).logAndShowError() })
    }
}
