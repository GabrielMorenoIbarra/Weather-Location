package com.gabrielmorenoibarra.weatherlocation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielmorenoibarra.generic.ui.view.fragment.BaseFragment
import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.response.GeoName
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.rv.LocationRvManager
import kotlinx.android.synthetic.main.fragment_locations.*
import kotlinx.android.synthetic.main.progress_bar.*
import kotlinx.android.synthetic.main.tv_no_results.*

class LocationsFragment : BaseFragment() {

    private lateinit var rvm: LocationRvManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvm()
    }

    private var listener: ((GeoName) -> Unit)? = null

    fun setListener(listener: (GeoName) -> Unit) {
        this.listener = listener
    }

    private fun initRvm() {
        rvm = LocationRvManager(rv, srl, tvNoResults, pb, "") {
            val geoName = it
            listener?.invoke(geoName)
        }
    }

    fun populate(s: String) {
        doWhenViewCreated {
            rvm.load(s)
        }
    }
}
