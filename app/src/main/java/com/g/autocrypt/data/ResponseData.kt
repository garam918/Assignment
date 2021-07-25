package com.g.autocrypt.data

data class ResponseData(
    val page : Int,
    val perPage : Int,
    val totalCount : Int,
    val currentCount : Int,
    val data : List<CenterInfoData>
)
