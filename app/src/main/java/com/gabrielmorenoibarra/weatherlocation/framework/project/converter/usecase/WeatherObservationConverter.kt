package com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase

import com.gabrielmorenoibarra.generic.extension.json.toObject
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.WeatherObservationPage
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.WeatherObservation
import com.google.gson.JsonElement

fun JsonElement.toWeatherObservation() = toObject<WeatherObservation>()
fun JsonElement.toWeatherObservationPage() = toObject<WeatherObservationPage>()
