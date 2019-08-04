package com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.keys.routes.response.GeoNameKeys
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.Page
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.GeoName
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoNamePage(
    @SerializedName(GeoNameKeys.ITEMS_NAME) var items: List<GeoName> = emptyList()
) : Page(), Parcelable
