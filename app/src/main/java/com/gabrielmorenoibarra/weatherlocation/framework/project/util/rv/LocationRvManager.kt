package com.gabrielmorenoibarra.weatherlocation.framework.project.util.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gabrielmorenoibarra.generic.util.manager.BaseRvManager
import com.gabrielmorenoibarra.weatherlocation.BuildConfig
import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.data.api.parser.routes.LocationApiParser
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.request.Location
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.GeoName
import com.gabrielmorenoibarra.weatherlocation.ui.adapter.rv.LocationAdapter

class LocationRvManager(rv: RecyclerView,
                        srl: SwipeRefreshLayout,
                        vNoResults: View,
                        query: String,
                        private val listener: (GeoName) -> Unit) :
        BaseRvManager<GeoName>(rv, srl, R.drawable.divider_vertical_1dp_s20, vNoResults, query = query) {

    override fun createAdapter(items: List<GeoName>): LocationAdapter {
        return LocationAdapter(items.toMutableList(), listener)
    }

    override fun get(nPage: Int, nItems: Int, listener: (List<GeoName>) -> Unit) {
        val location = Location(query)
        LocationApiParser().get(location,
                BuildConfig.USERNAME_IL_GEONAMES_SAMPLE,
                0, 20) {
            val page = it
            val items = page.items
            listener(items)
        }
    }

    fun load(s: String) {
        query = s
        reset()
    }
}
