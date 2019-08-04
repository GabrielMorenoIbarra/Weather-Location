package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BBox(
    val north: Float,
    val south: Float,
    val east: Float,
    val west: Float,
    val accuracyLevel: Int
) : Parcelable
