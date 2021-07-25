package com.g.autocrypt.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// 필요한 센터 정보들이며, PrimaryKey로 공공 데이터 포털에서 받아올 때 정의되어있는 ID 값을 사용했습니다.
// createAt과 updateAt이 null 값이 있을 수 있기 때문에 null 값을 허용시켰습니다.
@Entity
data class CenterEntity(
    @PrimaryKey val id : Int,
    val centerName : String,
    val sido : String,
    val sigungu : String,
    val facilityName : String,
    val zipCode : String,
    val address : String,
    val lat : String,
    val lng : String,
    val createAt : String?,
    val updateAt : String?,
    val centerType : String,
    val org : String,
    val phoneNumber : String
)