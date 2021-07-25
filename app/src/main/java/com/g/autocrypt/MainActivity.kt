package com.g.autocrypt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.g.autocrypt.databinding.ActivityMainBinding
import com.g.autocrypt.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    // 액티비티가 실행되자 마자 데이터 바인딩과 ViewModel을 초기화 했고, 바로 공공 데이터 포털에서 센터 정보를 받아오게 했습니다.
    private fun init() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        mainViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(MainViewModel::class.java)
        binding.viewModel = mainViewModel

        mainViewModel.getCenterInfo()
    }

}