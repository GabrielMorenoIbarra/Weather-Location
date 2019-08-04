package com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoName(
        var asciiName: String
) : Parcelable
