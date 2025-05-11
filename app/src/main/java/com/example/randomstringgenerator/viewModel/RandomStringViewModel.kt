package com.example.randomstringgenerator.viewModel

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.randomstringgenerator.database.AppDatabase
import com.example.randomstringgenerator.model.RandomString
import com.example.randomstringgenerator.repository.RandomStringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class RandomStringViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RandomStringRepository
    val allStrings: LiveData<List<RandomString>>
    val errorMessage = MutableLiveData<String?>()

    init {
        val dao = AppDatabase.getDatabase(application).randomStringDao()
        repository = RandomStringRepository(dao)
        allStrings = repository.allStrings
    }

    fun fetchRandomString(length: Int, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val uri = Uri.parse("content://com.iav.contestdataprovider/text")
                val bundle = Bundle().apply {
                    putInt(ContentResolver.QUERY_ARG_LIMIT, length)
                }

                val cursor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.contentResolver.query(uri, null, bundle, null)
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                cursor?.use {
                    if (it.moveToFirst()) {
                        val data = it.getString(it.getColumnIndexOrThrow("data"))
                        val json = JSONObject(data).getJSONObject("randomText")

                        val value = json.getString("value")
                        val length = json.getInt("length")
                        val created = json.getString("created")

                        val entity = RandomString(value = value, length = length, created = created)
                        repository.insert(entity)
                    }
                } ?: run {
                    errorMessage.postValue("No response from provider.")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun delete(item: RandomString) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }
}