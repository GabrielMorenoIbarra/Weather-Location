package com.gabrielmorenoibarra.weatherlocation.ui.adapter.rv

import android.view.View
import android.view.ViewGroup
import com.gabrielmorenoibarra.generic.extension.view.inflate
import com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader.BaseLoaderAdapter
import com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader.footer.FooterVerticalBaseLoaderAdapter
import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.GeoName
import kotlinx.android.synthetic.main.item_location.view.*

class LocationAdapter(items: MutableList<GeoName>,
                      val listener: (GeoName) -> Unit)
    : FooterVerticalBaseLoaderAdapter<GeoName>(
        items,
        listener = listener) {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<GeoName> {
        return Holder(parent)
    }

    class Holder(parent: ViewGroup) : BaseLoaderAdapter.ItemHolder<GeoName>(parent.inflate(R.layout.item_location)) {
        override fun View.bindItem(adapter: BaseLoaderAdapter<GeoName>, item: GeoName) {
            initTv(adapter, item)
        }

        private fun View.initTv(adapter: BaseLoaderAdapter<GeoName>, item: GeoName) {
            tv.text = item.asciiName
        }
    }
}
