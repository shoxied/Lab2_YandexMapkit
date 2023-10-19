package com.android.example.ternovoiiliaapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras.Empty
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class MainActivity : AppCompatActivity() {

    private var API:String = "76b1bf32-c4dd-4039-a5e0-a879a159d132"
    private var LOCATION:Point = Point(55.030264, 82.922684)
    private lateinit var mapView: MapView

    private val TapListener = MapObjectTapListener { _, point ->
        Toast.makeText(
            this@MainActivity,
            "Координаты точки (${point.longitude}, ${point.latitude})",
            Toast.LENGTH_SHORT
        ).show()
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey(API)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.mapview)

        mapView.mapWindow.map.move(
            CameraPosition(LOCATION, 17.0f, 0.0f, 0.0f),
            Animation(Animation.Type.LINEAR, 5.0f),
            null
        )

        val imageProvider = ImageProvider.fromResource(this, R.drawable.mark)
        val placemark = mapView.map.mapObjects.addPlacemark().apply {
            geometry = Point(55.030264, 82.922684)
            setIcon(imageProvider)
        }

        mapView.mapWindow.map.mapObjects.add
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}