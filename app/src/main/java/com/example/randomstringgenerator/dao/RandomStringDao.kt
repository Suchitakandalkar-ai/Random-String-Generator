package com.example.randomstringgenerator.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.randomstringgenerator.model.RandomString

@Dao
interface RandomStringDao {
    @Query("SELECT * FROM random_strings ORDER BY id DESC")
    fun getAll(): LiveData<List<RandomString>>

    @Insert
    suspend fun insert(randomString: RandomString)

    @Query("DELETE FROM random_strings")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(randomString: RandomString)
}