package com.gabrielmorenoibarra.weatherlocation.domain.model.page.usecase

import android.os.Parcelable
import com.gabrielmorenoibarra.weatherlocation.domain.model.page.Page
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Location
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationPage(
        var items: List<Location> = emptyList()
) : Page(), Parcelable
