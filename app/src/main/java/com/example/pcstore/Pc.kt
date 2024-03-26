package com.example.pcstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pcstore.databinding.PcActivityBinding

class Pc : AppCompatActivity() {
    lateinit var binding: PcActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PcActivityBinding.inflate(layoutInflater)




        setContentView(binding.root)
    }

}