package com.gabrielmorenoibarra.weatherlocation.data.api.manager.routes

import com.gabrielmorenoibarra.generic.util.Success
import com.gabrielmorenoibarra.weatherlocation.data.api.client.usecase.ApiClient
import com.gabrielmorenoibarra.weatherlocation.data.api.manager.ApiManager
import com.gabrielmorenoibarra.weatherlocation.data.api.service.routes.LocationApiService
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.Failure
import com.google.gson.JsonElement

class LocationApiManager : ApiManager() {

    private val apiService = ApiClient.get().create(LocationApiService::class.java)

    fun getLocation(name: String,
                    language: String,
                    nameRequired: Boolean,
                    style: String,
                    username: String,
                    nPage: Int,
                    nItems: Int,
                    success: Success<JsonElement>,
                    failure: Failure) {
        enqueue(apiService.getLocation(
                name,
                language,
                nameRequired,
                style,
                username,
                nPage,
                nItems),
                onComplete(success, failure))
    }
}
