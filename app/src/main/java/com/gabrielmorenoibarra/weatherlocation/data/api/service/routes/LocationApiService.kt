package com.gabrielmorenoibarra.weatherlocation.data.api.service.routes

import com.gabrielmorenoibarra.weatherlocation.domain.keys.Keys
import com.gabrielmorenoibarra.weatherlocation.domain.keys.routes.LocationKeys
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    companion object {
        private const val PAGE = Keys.PAGE
        private const val ITEMS = Keys.ITEMS
        private const val EXTENDED = Keys.EXTENDED

        private const val URL_LOCATION = LocationKeys.URL_LOCATION

        private const val QUERY_LOCATION = LocationKeys.QUERY_LOCATION
    }

    @GET(URL_LOCATION)
    fun getLocation(@Query(EXTENDED) extended: String,
                    @Query(PAGE) nPage: Int,
                    @Query(ITEMS) nItems: Int
    ): Call<JsonElement>
}
