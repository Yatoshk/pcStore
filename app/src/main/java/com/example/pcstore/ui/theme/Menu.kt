package com.example.pcstore.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pcstore.About
import com.example.pcstore.R

class Menu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_menu, container, false)

        view.findViewById<Button>(R.id.button_about)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, About::class.java))
            }

        return view
    }

}