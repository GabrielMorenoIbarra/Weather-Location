package com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes

import com.gabrielmorenoibarra.generic.util.Success
import com.gabrielmorenoibarra.weatherlocation.data.api.client.usecase.ApiClient
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.ApiManager
import com.gabrielmorenoibarra.weatherlocation.data.api.service.routes.WeatherApiService
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.Failure
import com.google.gson.JsonElement

class WeatherApiManager : ApiManager() {

    private val apiService = ApiClient.get().create(WeatherApiService::class.java)

    fun get(north: Float,
            south: Float,
            east: Float,
            west: Float,
            username: String,
            nPage: Int,
            nItems: Int,
            success: Success<JsonElement>,
            failure: Failure) {
        enqueue(apiService.get(
                north,
                south,
                east,
                west,
                username,
                nPage,
                nItems),
                onComplete(success, failure))
    }
}
