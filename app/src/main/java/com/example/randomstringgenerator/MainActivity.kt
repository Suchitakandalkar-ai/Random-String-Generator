package com.example.randomstringgenerator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomstringgenerator.adapter.RandomStringAdapter
import com.example.randomstringgenerator.databinding.ActivityMainBinding
import com.example.randomstringgenerator.viewModel.RandomStringViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RandomStringAdapter
    private val viewModel: RandomStringViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RandomStringAdapter { string ->
            viewModel.delete(string)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.buttonGenerate.setOnClickListener {
            val length = binding.editTextLength.text.toString().toIntOrNull()
            if (length != null && length > 0) {
                viewModel.fetchRandomString(length, this)
            } else {
                Toast.makeText(this, "Invalid length", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonClearAll.setOnClickListener {
            viewModel.deleteAll()
        }

        viewModel.allStrings.observe(this) {
            adapter.update(it)
        }

        viewModel.errorMessage.observe(this) {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.errorMessage.value = null
            }
        }
    }
}
