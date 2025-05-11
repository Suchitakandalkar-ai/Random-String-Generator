package com.example.randomstringgenerator.repository

import androidx.lifecycle.LiveData
import com.example.randomstringgenerator.dao.RandomStringDao
import com.example.randomstringgenerator.model.RandomString

class RandomStringRepository(private val dao: RandomStringDao) {

    val allStrings: LiveData<List<RandomString>> = dao.getAll()

    suspend fun insert(string: RandomString) = dao.insert(string)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun delete(string: RandomString) = dao.delete(string)
}