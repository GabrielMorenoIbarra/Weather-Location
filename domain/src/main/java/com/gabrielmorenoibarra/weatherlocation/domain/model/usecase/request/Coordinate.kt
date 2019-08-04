package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.request

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.keys.Keys
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinate(
        var north: Float,
        var south: Float,
        var east: Float,
        var west: Float,
        var username: String = Keys.USERNAME_IL_GEONAMES_SAMPLE
) : Parcelable
