package com.g.autocrypt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Room을 사용하기 위해 추상 클래스를 정의했습니다.
@Database(entities = [CenterEntity::class], version = 1)
abstract class CenterDatabase : RoomDatabase() {
    abstract fun centerDao() : CenterDao

    companion object {
        private var instance: CenterDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : CenterDatabase? {
            if (instance == null) {
                synchronized(CenterDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CenterDatabase::class.java,
                        "center-database"
                    ).build()
                }
            }
            return instance
        }

    }
}