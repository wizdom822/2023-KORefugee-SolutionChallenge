package com.example.korefugee.Map


import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import java.util.*


class MapActivity : AppCompatActivity(), OnMapReadyCallback
{

    var mGoogleMap: GoogleMap? = null

    lateinit var accesstoken:String
    lateinit var refreshtoken:String

    //Variables
    val local = Locale("en_us", "United States")


    // GoogleApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.korefugee.R.layout.activity_map)
        if (intent.hasExtra("accesstoken") && intent.hasExtra("refreshtoken")) {
            accesstoken = intent.getStringExtra("accesstoken").toString()
            refreshtoken =  intent.getStringExtra("refreshtoken").toString()
        }

        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(com.example.korefugee.R.id.layout_map, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
        }



    override fun onMapReady(googleMap: GoogleMap) {

        // Set the map coordinates to Kyoto Japan.
        val seoul = LatLng(37.566510,126.978403)
        val busan = LatLng(30.566510,100.978403)

        // Add a marker on the map coordinates.
        val position = CameraPosition.Builder()
            .target(seoul)
            .zoom(16f)
            .build()
        googleMap.moveCamera((CameraUpdateFactory.newCameraPosition(position)))

        googleMap.addMarker(
            MarkerOptions()
            .position(seoul)
            .title("here")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(seoul))
    }
    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
    }





