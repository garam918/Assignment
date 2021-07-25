package com.g.autocrypt.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CenterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(center : CenterEntity)

    @Query("SELECT * FROM CenterEntity")
    fun getAll() : LiveData<List<CenterEntity>>
}