package com.example.pcstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pcstore.databinding.CoreActivityBinding

class Cpu : AppCompatActivity() {
    lateinit var binding: CoreActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CoreActivityBinding.inflate(layoutInflater)




        setContentView(binding.root)
    }
}