package com.gabrielmorenoibarra.weatherlocation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.rv.LocationRvManager
import kotlinx.android.synthetic.main.fragment_locations.*
import kotlinx.android.synthetic.main.tv_no_results.*
import org.jetbrains.anko.support.v4.toast

class LocationsFragment : Fragment() {

    companion object {
        fun newInstance(): LocationsFragment = LocationsFragment()
    }

    private lateinit var rvm: LocationRvManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvm()
    }

    private fun initRvm() {
        rvm = LocationRvManager(rv, srl, tvNoResults) {
            val notification = it

            toast("Item pressed") // TODO
        }
        rvm.load()
    }
}
