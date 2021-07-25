package com.g.autocrypt.network

import com.g.autocrypt.data.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("15077586/v1/centers")
    fun getCenter(
        @Query("page") page : Int,
        @Query("perPage") perPage : Int,
        @Query("returnType") returnType : String?,
        @Query("serviceKey") serviceKey : String
    ) : Call<ResponseData>
}