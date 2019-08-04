package com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase

import com.gabrielmorenoibarra.generic.extension.json.toObject
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.LocationPage
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Location
import com.google.gson.JsonElement

fun JsonElement.toLocation() = toObject<Location>()
fun JsonElement.toLocationPage() = toObject<LocationPage>()