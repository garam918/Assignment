package com.g.autocrypt.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DB에 데이터를 저장하는 Method와 저장된 모든 데이터를 불러오는 Method만을 구현했습니다.
@Dao
interface CenterDao {
    // 데이터를 저장하는 도중 Method가 중지되는 것을 방지하기 위해 suspend 키워드를 사용했습니다.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(center : CenterEntity)

    @Query("SELECT * FROM CenterEntity")
    fun getAll() : LiveData<List<CenterEntity>>
}