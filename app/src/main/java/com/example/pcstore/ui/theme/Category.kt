package com.example.pcstore.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.pcstore.Case
import com.example.pcstore.Cooling
import com.example.pcstore.Core
import com.example.pcstore.Motherboard
import com.example.pcstore.Pc
import com.example.pcstore.Power
import com.example.pcstore.R
import com.example.pcstore.Ram
import com.example.pcstore.Storage
import com.example.pcstore.Videocard

class Category : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_category, container, false)

        view.findViewById<ImageButton>(R.id.button_motherboard)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Motherboard::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_cpu)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Core::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_videocard)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Videocard::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_ram)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Ram::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_ssd)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Storage::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_power)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Power::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_cooling)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Cooling::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_case)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Case::class.java))
            }
        view.findViewById<ImageButton>(R.id.button_pc)
            .setOnClickListener {
                view.context.startActivity(Intent(view.context, Pc::class.java))
            }
        return view
    }



}