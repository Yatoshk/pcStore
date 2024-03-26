package com.example.pcstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pcstore.databinding.HomeActivityBinding
import com.example.pcstore.ui.theme.Bucket
import com.example.pcstore.ui.theme.Category
import com.example.pcstore.ui.theme.Favorites
import com.example.pcstore.ui.theme.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView

//import androidx.appcompat.widget.Toolbar
class MainActivity : AppCompatActivity() {

    lateinit var binding: HomeActivityBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbarHome)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)

/*
        val btnSrch = findViewById<Button>(R.id.search_ic)

        btnSrch.setOnClickListener {

        }*/


        loadFragment(Category())
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.category -> {
                    loadFragment(Category())
                    true
                }
                R.id.busket -> {
                    loadFragment(Bucket())
                    true
                }
                R.id.favorites -> {
                    loadFragment(Favorites())
                    true
                }
                R.id.menu -> {
                    loadFragment(Menu())
                    true
                }
                else -> { false }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}

