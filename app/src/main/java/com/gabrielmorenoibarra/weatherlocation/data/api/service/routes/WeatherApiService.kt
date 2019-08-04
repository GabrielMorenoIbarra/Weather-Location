package com.gabrielmorenoibarra.weatherlocation.data.api.service.routes

import com.gabrielmorenoibarra.weatherlocation.domain.keys.Keys
import com.gabrielmorenoibarra.weatherlocation.domain.keys.routes.request.WeatherKeys
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    companion object {
        private const val PAGE = Keys.PAGE
        private const val ITEMS = Keys.ITEMS

        private const val BASE_PATH = WeatherKeys.BASE_PATH

        private const val QUERY_NORTH = WeatherKeys.QUERY_NORTH
        private const val QUERY_SOUTH = WeatherKeys.QUERY_SOUTH
        private const val QUERY_EAST = WeatherKeys.QUERY_EAST
        private const val QUERY_WEST = WeatherKeys.QUERY_WEST
        private const val QUERY_USERNAME = Keys.QUERY_USERNAME
    }

    @GET(BASE_PATH)
    fun get(@Query(QUERY_NORTH) north: Double,
            @Query(QUERY_SOUTH) south: Double,
            @Query(QUERY_EAST) east: Double,
            @Query(QUERY_WEST) west: Double,
            @Query(QUERY_USERNAME) username: String,
            @Query(PAGE) nPage: Int,
            @Query(ITEMS) nItems: Int
    ): Call<JsonElement>
}
