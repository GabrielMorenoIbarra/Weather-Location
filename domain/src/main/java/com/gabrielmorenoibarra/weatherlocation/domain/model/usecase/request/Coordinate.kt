package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.request

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.keys.Keys
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinate(
        val north: Float,
        val south: Float,
        val east: Float,
        val west: Float
) : Parcelable
