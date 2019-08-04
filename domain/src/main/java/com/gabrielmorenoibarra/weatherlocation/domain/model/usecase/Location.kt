package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.keys.routes.LocationKeys
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    var name: String,
    var language: String = LocationKeys.LANGUAGE_EN,
    var nameRequired: Boolean = true,
    var style: String = LocationKeys.STYLE_FULL,
    var username: String = LocationKeys.USERNAME_IL_GEONAMES_SAMPLE
) : Parcelable
