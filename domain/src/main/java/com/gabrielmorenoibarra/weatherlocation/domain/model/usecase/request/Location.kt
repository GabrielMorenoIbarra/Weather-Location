package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.request

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.keys.Keys
import com.gabrielmorenoibarra.weatherlocation.domain.keys.routes.request.LocationKeys
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
        val name: String,
        val language: String = LocationKeys.LANGUAGE_EN,
        val nameRequired: Boolean = true,
        val style: String = LocationKeys.STYLE_FULL
) : Parcelable
