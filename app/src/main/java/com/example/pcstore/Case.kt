package com.example.pcstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pcstore.databinding.CaseActivityBinding

class Case : AppCompatActivity() {
    lateinit var binding: CaseActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CaseActivityBinding.inflate(layoutInflater)




        setContentView(binding.root)
    }

}