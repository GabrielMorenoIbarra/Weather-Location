package com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes

import com.gabrielmorenoibarra.generic.util.Success
import com.gabrielmorenoibarra.weatherlocation.data.api.client.usecase.ApiClient
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.ApiManager
import com.gabrielmorenoibarra.weatherlocation.data.api.service.routes.WeatherApiService
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.Failure
import com.google.gson.JsonElement

class WeatherApiManager : ApiManager() {

    private val apiService = ApiClient.get().create(WeatherApiService::class.java)

    fun get(north: Double,
            south: Double,
            east: Double,
            west: Double,
            username: String,
            success: Success<JsonElement>,
            failure: Failure) {
        enqueue(apiService.get(
                north,
                south,
                east,
                west,
                username),
                onComplete(success, failure))
    }
}
