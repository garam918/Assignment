package com.g.autocrypt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.g.autocrypt.databinding.ActivityNaverMapBinding
import com.g.autocrypt.viewModel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker

class NaverMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding : ActivityNaverMapBinding
    private lateinit var mapViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_naver_map)
        binding.lifecycleOwner = this

        mapViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(MainViewModel::class.java)

        binding.mapViewModel = mapViewModel

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)


    }

    @UiThread
    override fun onMapReady(naverMap : NaverMap) {
        addMarker(naverMap)
    }

    private fun addMarker(naverMap: NaverMap) {
        mapViewModel.getAllCenter().observe(this, {
            for(i in it.indices) {
                val marker = Marker()

                when(it[i].centerType) {
                    "지역" -> {
                        marker.iconTintColor = Color.GREEN
                    }
                    "중앙/권역" -> {
                        marker.iconTintColor = Color.BLUE
                    }
                }
                marker.captionText = it[i].centerName
                marker.position = LatLng(it[i].lat.toDouble(),it[i].lng.toDouble())
                marker.map = naverMap
            }
        })
    }

}