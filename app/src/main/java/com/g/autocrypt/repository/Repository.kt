package com.g.autocrypt.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.g.autocrypt.BuildConfig
import com.g.autocrypt.data.ResponseData
import com.g.autocrypt.database.CenterDatabase
import com.g.autocrypt.database.CenterEntity
import com.g.autocrypt.network.NetworkController
import com.g.autocrypt.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (application: Application) {

    private val networkService : NetworkService by lazy {
        NetworkController.instance.networkService
    }

    private val centerDatabase = CenterDatabase.getInstance(application)!!
    private val centerDao = centerDatabase.centerDao()
    private val centers : LiveData<List<CenterEntity>> = centerDao.getAll()

    fun getAll() : LiveData<List<CenterEntity>> {
        return centers
    }

    fun getCenterInfo() {
        repeat(10) {
            networkService.getCenter(
                (it+1),
                10,
                "JSON",
                BuildConfig.data_service_key
                )
                .enqueue(object : Callback<ResponseData> {
                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<ResponseData>,
                        response: Response<ResponseData>
                    ) {
                        repeat(10) { i ->

                            val item = response.body()!!.data[i]
                            Log.e("test", item.toString())
                            CoroutineScope(Dispatchers.IO).launch {
                                centerDao.insert(
                                    CenterEntity(
                                        item.id,
                                        item.centerName,
                                        item.sido,
                                        item.sigungu,
                                        item.facilityName,
                                        item.zipCode,
                                        item.address,
                                        item.lat,
                                        item.lng,
                                        item.createAt.toString(),
                                        item.updateAt.toString(),
                                        item.centerType,
                                        item.org,
                                        item.phoneNumber
                                    )
                                )
                            }
                        }
                    }
                })
        }
    }
}