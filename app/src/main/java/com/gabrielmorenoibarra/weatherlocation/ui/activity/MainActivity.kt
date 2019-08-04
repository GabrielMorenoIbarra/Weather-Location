package com.gabrielmorenoibarra.weatherlocation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielmorenoibarra.weatherlocation.R
import com.gabrielmorenoibarra.weatherlocation.ui.adapter.WordListAdapter
import com.gabrielmorenoibarra.weatherlocation.viewmodel.WordViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var wordViewModel: WordViewModel
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = initAdapter()
        initViewModel(adapter)
        initGoogleMap()
    }

    private fun initAdapter(): WordListAdapter {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        return adapter
    }

    private fun initViewModel(adapter: WordListAdapter) {
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this,
                Observer { words ->
                    words?.let { adapter.setWords(it) }
                })
    }

    private fun initGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            this.googleMap = it

            // Add a marker in Sydney and move the camera
            val sydney = LatLng(-34.0, 151.0)
            this.googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }
}
