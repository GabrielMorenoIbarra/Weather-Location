package com.gabrielmorenoibarra.weatherlocation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielmorenoibarra.generic.extension.isConnected
import com.gabrielmorenoibarra.generic.extension.view.gone
import com.gabrielmorenoibarra.generic.extension.view.hideKeyboard
import com.gabrielmorenoibarra.generic.extension.view.visible
import com.gabrielmorenoibarra.generic.util.manager.SearchManager
import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.domain.model.usecase.Word
import com.gabrielmorenoibarra.weatherlocation.ui.adapter.WordListAdapter
import com.gabrielmorenoibarra.weatherlocation.ui.fragment.LocationsFragment
import com.gabrielmorenoibarra.weatherlocation.viewmodel.WordViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_search.*
import kotlinx.android.synthetic.main.tv_no_results.*

class MainActivity
    : AppCompatActivity()
    , OnMapReadyCallback, LifecycleOwner {

    private lateinit var wordViewModel: WordViewModel
    private lateinit var googleMap: GoogleMap

    private lateinit var locationFragment: LocationsFragment

    private var etHasFocus = false
    private var showNoResults = false
    private var showHistoric = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
        initGoogleMap()
        initClSearch()
        initTvCancel()
        val adapter = initAdapter()
        initViewModel(adapter)
    }

    fun initFragments() {
        locationFragment = supportFragmentManager.findFragmentById(R.id.fLocations) as LocationsFragment
        locationFragment.setListener {
            val geoName = it
            val name = geoName.asciiName
            saveToHistoric(name)
            val latitude = geoName.lat.toDouble()
            val longitude = geoName.lng.toDouble()
            val latLng = LatLng(latitude, longitude)
            goToPlace(latLng)
        }
    }

    fun saveToHistoric(s: String) {
        wordViewModel.insert(Word(s))
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            this.googleMap = it

            val latLng = LatLng(40.4165, -3.70256)
            this.googleMap.addMarker(MarkerOptions().position(latLng).title(getString(R.string.you_are_here)))
            goToPlace(latLng)
        }
    }

    private fun goToPlace(latLng: LatLng) {
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    private fun initGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initClSearch() {
        SearchManager(et, ib) { s ->
            fetch(s)
        }

        et.setOnFocusChangeListener { _, hasFocus ->
            etHasFocus = hasFocus
            if (hasFocus) {
                showHistoricPerform()
            } else {
                hideHistoricPerform()
            }
        }
    }

    private fun hideHistoricPerform() {
        clHistoric.gone()
        clResults.visible()
        tvCancel.gone()
        if (showNoResults) tvNoResults.visible()
    }

    private fun showHistoricPerform() {
        clHistoric.visible()
        clResults.gone()
        tvCancel.visible()
        tvNoResults.gone()
    }

    private fun initTvCancel() {
        tvCancel.setOnClickListener {
            clearEtFocusPerform()
        }
    }

    private fun clearEtFocusPerform() {
        et.clearFocus()
        hideKeyboard()
    }

    private fun initAdapter(): WordListAdapter {
        val adapter = WordListAdapter(this) {
            fetch(it)
        }
        rvHistoric.adapter = adapter
        rvHistoric.layoutManager = LinearLayoutManager(this)
        return adapter
    }

    private fun initViewModel(adapter: WordListAdapter) {
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this,
            Observer { words ->
                words?.let { adapter.setWords(it) }
            })
    }

    private fun fetch(s: String) {
        val connected = isConnected()
        if (connected) {
            if (s.trim().isEmpty() && etHasFocus) {
                showHistoricPerform()
            } else {
                hideHistoricPerform()
                locationFragment.populate(s)
            }
        }
    }
}
