package com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.keys.routes.response.WeatherObservationKeys
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.Page
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.GeoName
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.WeatherObservation
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherObservationPage(
        @SerializedName(WeatherObservationKeys.ITEMS_NAME) var items: List<WeatherObservation> = emptyList()
) : Page(), Parcelable
