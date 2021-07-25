package com.g.autocrypt.data

data class CenterInfoData(
    val id : Int,
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
