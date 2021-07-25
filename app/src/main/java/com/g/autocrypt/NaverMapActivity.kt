package com.g.autocrypt

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.g.autocrypt.database.CenterEntity
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

        init()

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        // 마커를 생성하기 위해 NaverMap 객체가 필요해 사용했습니다.
        mapFragment.getMapAsync(this)

    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_naver_map)
        binding.lifecycleOwner = this

        mapViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(MainViewModel::class.java)

        binding.mapViewModel = mapViewModel
    }

    @UiThread
    override fun onMapReady(naverMap : NaverMap) {
        addMarker(naverMap)
    }

    private fun addMarker(naverMap: NaverMap) {

        // viewModel에서 DB 내의 모든 센터 정보를 받아와서 마커를 생성했습니다.
        mapViewModel.getAllCenter().observe(this, {
            for(i in it.indices) {
                val marker = Marker()

                // centerType에 따라 지역이면 녹색, 중앙/권역이면 파란색으로 마커를 설정했습니다.
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
                marker.setOnClickListener { overlay ->
                    toastMessage(it[i])

                    true
                }
            }
        })
    }

    // 과제 요구사항에 데이터를 toast로 출력한다고 되어있어 Dialog나 InfoWindow를 활용하지 않고 Toast 메시지를 사용했습니다.
    private fun toastMessage(centerEntity: CenterEntity) {
        Toast.makeText(this@NaverMapActivity,
            "centerName : ${centerEntity.centerName}\n" + "id : ${centerEntity.id}\n" + "sido : ${centerEntity.sido}\n"
                + "sigungu : ${centerEntity.sigungu}\n" + "facilityName : ${centerEntity.facilityName}\n"
                + "zipCode : ${centerEntity.zipCode}\n" + "address : ${centerEntity.address}\n" +
                "LatLag : ${centerEntity.lat},${centerEntity.lng}\n" + "createAt : ${centerEntity.createAt}\n"
                + "updateAt : ${centerEntity.updateAt}\n" + "centerType : ${centerEntity.centerType}\n"
                + "org : ${centerEntity.org}\n" + "phoneNumber : ${centerEntity.phoneNumber}\n"
            ,Toast.LENGTH_LONG).show()

    }

}