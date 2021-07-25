package com.g.autocrypt.data

// 공공 데이터 포털에서 데이터를 받을 때 사용할 Data Class를 정의 했습니다.
data class ResponseData(
    val page : Int,
    val perPage : Int,
    val totalCount : Int,
    val currentCount : Int,
    val data : List<CenterInfoData>
)
