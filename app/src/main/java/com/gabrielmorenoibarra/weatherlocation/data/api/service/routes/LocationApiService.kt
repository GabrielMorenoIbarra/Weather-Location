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

        private const val URL_LOCATION = LocationKeys.URL_LOCATION

        private const val QUERY_NAME = LocationKeys.QUERY_NAME
        private const val QUERY_LANG = LocationKeys.QUERY_LANG
        private const val QUERY_NAME_REQUIRED = LocationKeys.QUERY_NAME_REQUIRED
        private const val QUERY_STYLE = LocationKeys.QUERY_STYLE
        private const val QUERY_USERNAME = LocationKeys.QUERY_USERNAME
    }

    @GET(URL_LOCATION)
    fun getLocation(@Query(QUERY_NAME) name: String,
                    @Query(QUERY_LANG) language: String,
                    @Query(QUERY_NAME_REQUIRED) nameRequired: Boolean,
                    @Query(QUERY_STYLE) style: String,
                    @Query(QUERY_USERNAME) username: String,
                    @Query(PAGE) nPage: Int,
                    @Query(ITEMS) nItems: Int
    ): Call<JsonElement>
}
