package com.example.randomstringgenerator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomstringgenerator.dao.RandomStringDao
import com.example.randomstringgenerator.model.RandomString

@Database(entities = [RandomString::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun randomStringDao(): RandomStringDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "random_string_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}