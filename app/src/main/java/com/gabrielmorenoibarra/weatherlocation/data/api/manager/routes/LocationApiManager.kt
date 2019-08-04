package com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes

import com.gabrielmorenoibarra.generic.util.Success
import com.gabrielmorenoibarra.weatherlocation.data.api.client.usecase.ApiClient
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.ApiManager
import com.gabrielmorenoibarra.weatherlocation.data.api.service.routes.LocationApiService
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.Failure
import com.google.gson.JsonElement

class LocationApiManager : ApiManager() {

    private val apiService = ApiClient.get().create(LocationApiService::class.java)

    fun getLocation(extended: String,
                    nPage: Int,
                    nItems: Int,
                    success: Success<JsonElement>,
                    failure: Failure) {
        enqueue(apiService.getLocation(extended, nPage, nItems),
                onComplete(success, failure))
    }
}
