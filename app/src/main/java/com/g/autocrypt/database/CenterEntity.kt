package com.g.autocrypt.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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