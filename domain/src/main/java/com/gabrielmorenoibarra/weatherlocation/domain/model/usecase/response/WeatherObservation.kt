package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherObservation(
        var temperature: String
) : Parcelable
