package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinate(val north: Double,
                      val south: Double,
                      val east: Double,
                      val west: Double,
                      val accuracyLevel: Int) : Parcelable
