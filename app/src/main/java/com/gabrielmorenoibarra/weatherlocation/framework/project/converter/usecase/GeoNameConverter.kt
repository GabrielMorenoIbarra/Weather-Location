package com.gabrielmorenoibarra.weatherlocation.framework.project.converter.usecase

import com.gabrielmorenoibarra.generic.extension.json.toObject
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase.GeoNamePage
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.GeoName
import com.google.gson.JsonElement

fun JsonElement.toGeoName() = toObject<GeoName>()
fun JsonElement.toGeoNamePage() = toObject<GeoNamePage>()
