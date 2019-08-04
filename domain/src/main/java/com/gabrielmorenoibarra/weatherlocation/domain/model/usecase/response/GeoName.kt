package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Coordinate
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoName(
        var asciiName: String,
        @SerializedName("bBox") var coordinate: Coordinate,
        var lat: String,
        var lng: String
) : Parcelable
