
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.ImageButton
//import android.widget.Toast
//import com.google.android.gms.common.api.Status
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.Marker
//import com.google.android.gms.maps.model.MarkerOptions
//import com.google.android.libraries.places.api.Places
//import com.google.android.libraries.places.api.model.Place
//import com.google.android.libraries.places.widget.AutocompleteSupportFragment
//import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
//
//class MapActivity : AppCompatActivity(), OnMapReadyCallback {
//    private var mGoogleMap:GoogleMap? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_map)
//
//        Places.initialize(applicationContext, getString(R.string.google_maps_api_key))
//        autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
//                as AutocompleteSupportFragment
//        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG))
//        autocompleteFragment.setOnPlaceSelectedListener(object: PlaceSelectionListener{
//            override fun onError(p0: Status) {
//                Toast.makeText(this@MapActivity, "Some Error in Search", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onPlaceSelected(place: Place) {
//                val add = place.address
//                val id = place.id
//                val latLng = place.latLng
//                val marker = latLng?.let { addMarker(it) }
//                if (marker != null) {
//                    marker.title = add
//                }
//                if (marker != null) {
//                    marker.snippet = id
//                }
//                if (latLng != null) {
//                    zoomOnMap(latLng)
//                }
//            }
//
//        })
//
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.mapFragment) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        val button2 = findViewById<ImageButton>(R.id.imageButton)
//        button2.setOnClickListener {
//            val intent = Intent(this, PayActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun zoomOnMap(latLng: LatLng)
//    {
//        val neaLngZoom = CameraUpdateFactory.newLatLngZoom(latLng,12f)
//        mGoogleMap?.animateCamera(neaLngZoom)
//    }
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        mGoogleMap = googleMap
//
//        // add marker
//        addMarker(LatLng(12.9418,77.5662))
//
//        // add draggable marker
//        addDraggableMarker(LatLng(12.9418,77.5662))
//
//    }
//
//    private fun addMarker(position: LatLng): Marker
//    {
//        val marker = mGoogleMap?.addMarker(MarkerOptions()
//            .position(position)
//            .title("Marker")
//        )
//
//        return marker!!
//    }
//
//    private fun addDraggableMarker(position: LatLng)
//    {
//        mGoogleMap?.addMarker(MarkerOptions()
//            .position(position)
//            .title("Draggable Marker")
//            .draggable(true)
//        )
//    }
//
//}

package com.example.tollapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap:GoogleMap? = null
    private lateinit var autocompleteFragment: AutocompleteSupportFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Places.initialize(applicationContext, getString(R.string.google_maps_api_key))
        autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG))
        autocompleteFragment.setOnPlaceSelectedListener(object: PlaceSelectionListener{
            override fun onError(p0: Status) {
                Toast.makeText(this@MapActivity, "Some Error in Search", Toast.LENGTH_SHORT).show()
            }

            override fun onPlaceSelected(place: Place) {
                val add = place.address
                val id = place.id
                val latLng = place.latLng
                val marker = latLng?.let { addMarker(it) }
                if (marker != null) {
                    marker.title = add
                }
                if (marker != null) {
                    marker.snippet = id
                }
                if (latLng != null) {
                    zoomOnMap(latLng)
                }
            }

        })

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun zoomOnMap(latLng: LatLng)
    {
        val newlatLngZoom = CameraUpdateFactory.newLatLngZoom(latLng,12f)
        mGoogleMap?.animateCamera(newlatLngZoom)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap

//        //simple marker
//        mGoogleMap?.addMarker(MarkerOptions()
//            .position(LatLng(12.9418,77.5662))
//            .title("Marker")
//        )

        // add marker
        addMarker(LatLng(12.9418,77.5662))

        // add draggable marker
        addDraggableMarker(LatLng(12.9418,77.5662))

    }

    private fun addMarker(position: LatLng): Marker
    {
        val marker = mGoogleMap?.addMarker(MarkerOptions()
            .position(position)
            .title("Marker")
        )

        return marker!!
    }

    private fun addDraggableMarker(position: LatLng)
    {
        mGoogleMap?.addMarker(MarkerOptions()
            .position(position)
            .title("Draggable Marker")
            .draggable(true)
        )
    }
}