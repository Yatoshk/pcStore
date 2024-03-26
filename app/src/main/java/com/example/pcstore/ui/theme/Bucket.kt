package com.example.pcstore.ui.theme

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.pcstore.BucketItem
import com.example.pcstore.DataBase
import com.example.pcstore.ItemsDao
import com.example.pcstore.R


class Bucket : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bucket, container, false)

        var db: DataBase = DataBase.getDatabaseBucket(this)

        val dao = db.getDao()

        val grid = view.findViewById<GridLayout>(R.id.grid_bucket)

        db.getDao().getBucketsItems().asLiveData().observe(viewLifecycleOwner) { it ->
            grid.removeAllViews()
            var i = 0
            it.forEach{
                createListDynamically(i, grid, dao, it)
                i++
            }
        }

        db.getDao().sumForBucket().asLiveData().observe(viewLifecycleOwner) {
            val textSum = view.findViewById<TextView>(R.id.resultSum)
            if (it != null)
                textSum.text = "${it}"
            else
                textSum.text = "0"
        }

        return view
    }


    private fun createListDynamically(row: Int, grid: GridLayout, dao: ItemsDao, it: BucketItem) {
        val id = it.idItem
        val type = it.type
        val brand = it.brand
        val name = it.name
        val comparisonState = it.comparison
        val price = it.price
        val favoritesState: Boolean = it.favorites
        var amount: Int = it.amount

        val paramGrid = GridLayout.LayoutParams()
        paramGrid.height = GridLayout.LayoutParams.WRAP_CONTENT
        paramGrid.width = GridLayout.LayoutParams.MATCH_PARENT
        paramGrid.columnSpec = GridLayout.spec(0)
        paramGrid.rowSpec = GridLayout.spec(row)
        paramGrid.setMargins(50, 15, 50 ,50)

        val tableLayout1 = TableLayout(this.context)
        tableLayout1.layoutParams = paramGrid

        val tableLayout2 = TableLayout(this.context)
        tableLayout2.layoutParams = paramGrid
        tableLayout2.setPadding(50,360,0,0)

        val paramTable = TableLayout.LayoutParams()
        paramTable.height = TableLayout.LayoutParams.MATCH_PARENT
        paramTable.width = TableLayout.LayoutParams.MATCH_PARENT
        tableLayout1.setBackgroundResource(R.drawable.itembucket)

        val tableRow1 = TableRow(this.context)

        val tableRow2 = TableRow(this.context)

        val paramTableRow = TableRow.LayoutParams()
        paramTableRow.height = TableLayout.LayoutParams.WRAP_CONTENT
        paramTableRow.width = TableLayout.LayoutParams.WRAP_CONTENT
        paramTableRow.gravity = Gravity.LEFT


        val pic = ImageView(this.context)
        when(type){
            "Motherboard" -> pic.setImageResource(R.drawable.motherboard2)
            "CPU" -> pic.setImageResource(R.drawable.core2)
            "Videocard" -> pic.setImageResource(R.drawable.videocard2)
            "Ram" -> pic.setImageResource(R.drawable.ram2)
            "Storage" -> pic.setImageResource(R.drawable.ssd2)
            "Power" -> pic.setImageResource(R.drawable.powerunit2)
            "Cooling" -> pic.setImageResource(R.drawable.cooling2)
        }
        pic.layoutParams = paramTableRow
        pic.setPadding(0, 0, 0, 60)

        val infoText = TextView(this.context)
        infoText.layoutParams = paramTableRow
        infoText.text = "${type}\n${brand} ${name}\n\n${price}p"
        infoText.setTextColor(0xFFFFFFFF.toInt())
        infoText.width = 450
        infoText.setPadding(0, 50, 0, 60)

        tableRow1.addView(pic)
        tableRow1.addView(infoText)

        val mark = ImageButton(this.context)
        mark.layoutParams = paramTableRow
        mark.setBackgroundColor(0xFF6D7AC2.toInt())
        when (favoritesState) {
            false -> mark.setImageResource(R.drawable.bookmark_nonactive)
            true -> mark.setImageResource(R.drawable.bookmark_active)
        }
        mark.setPadding(0, 0, 0 ,20)

        mark.setOnClickListener{
            when (favoritesState) {
                true -> {
                    mark.setImageResource(R.drawable.bookmark_nonactive)
                    Thread{
                        when(type){
                            "Motherboard" -> dao.updateFavoritesMotherboardById(false, id!!)
                            "CPU" -> dao.updateFavoritesCoreById(false, id!!)
                            "Videocard" -> dao.updateFavoritesVideocardById(false, id!!)
                            "Ram" -> dao.updateFavoritesRamById(false, id!!)
                            "Storage" -> dao.updateFavoritesStorageById(false, id!!)
                            "Power" -> dao.updateFavoritesPowerById(false, id!!)
                            "Cooling" -> dao.updateFavoritesCoolingById(false, id!!)
                        }
                    }.start()
                }
                false -> {
                    mark.setImageResource(R.drawable.bookmark_active)
                    Thread{
                        when(type){
                            "Motherboard" -> dao.updateFavoritesMotherboardById(true, id!!)
                            "CPU" -> dao.updateFavoritesCoreById(true, id!!)
                            "Videocard" -> dao.updateFavoritesVideocardById(true, id!!)
                            "Ram" -> dao.updateFavoritesRamById(true, id!!)
                            "Storage" -> dao.updateFavoritesStorageById(true, id!!)
                            "Power" -> dao.updateFavoritesPowerById(true, id!!)
                            "Cooling" -> dao.updateFavoritesCoolingById(true, id!!)

                        }
                    }.start()
                }
            }
        }

        val comparison = ImageButton(this.context)
        comparison.layoutParams = paramTableRow
        comparison.setBackgroundColor(0xFF6D7AC2.toInt())
        when (comparisonState) {
            false -> comparison.setImageResource(R.drawable.comparision)
            true -> comparison.setImageResource(R.drawable.comparision_active)
        }
        comparison.setPadding(60, 0, 0 ,20)
        comparison.setOnClickListener{
            when (comparisonState) {
                true -> {
                    comparison.setImageResource(R.drawable.comparision)
                    Thread{
                        when(type){
                            "Motherboard" -> dao.updateComparisonMotherboardById(false, id!!)
                            "CPU" -> dao.updateComparisonCoreById(false, id!!)
                            "Videocard" -> dao.updateComparisonVideocardById(false, id!!)
                            "Ram" -> dao.updateComparisonRamById(false, id!!)
                            "Storage" -> dao.updateComparisonStorageById(false, id!!)
                            "Power" -> dao.updateComparisonPowerById(false, id!!)
                            "Cooling" -> dao.updateComparisonCoolingById(false, id!!)
                        }
                    }.start()
                }
                false -> {
                    comparison.setImageResource(R.drawable.comparision_active)
                    Thread{
                        when(type){
                            "Motherboard" -> dao.updateComparisonMotherboardById(true, id!!)
                            "CPU" -> dao.updateComparisonCoreById(true, id!!)
                            "Videocard" -> dao.updateComparisonVideocardById(true, id!!)
                            "Ram" -> dao.updateComparisonRamById(true, id!!)
                            "Storage" -> dao.updateComparisonStorageById(true, id!!)
                            "Power" -> dao.updateComparisonPowerById(true, id!!)
                            "Cooling" -> dao.updateComparisonCoolingById(true, id!!)
                        }
                    }.start()
                }
            }
        }
        val trash = ImageButton(this.context)
        trash.layoutParams = paramTableRow
        trash.setBackgroundColor(0xFF6D7AC2.toInt())
        trash.setImageResource(R.drawable.trash)
        trash.setPadding(60, 0, 0 ,20)
        trash.setOnClickListener{
            Thread{
                when(type){
                    "Motherboard" -> {
                        dao.updateBucketMotherboardById(false, id!!)
                        dao.updateAmountMotherboardById(1, id!!)
                    }
                    "CPU" ->{
                        dao.updateBucketCoreById(false, id!!)
                        dao.updateAmountCoreById(1, id!!)
                    }
                    "Videocard" ->{
                        dao.updateBucketVideocardById(false, id!!)
                        dao.updateAmountVideocardById(1, id!!)
                    }
                    "Ram" ->{
                        dao.updateBucketRamById(false, id!!)
                        dao.updateAmountRamById(1, id!!)
                    }
                    "Storage" ->{
                        dao.updateBucketStorageById(false, id!!)
                        dao.updateAmountStorageById(1, id!!)
                    }
                    "Power" ->{
                        dao.updateBucketPowerById(false, id!!)
                        dao.updateAmountPowerById(1, id!!)
                    }
                    "Cooling" ->{
                        dao.updateBucketCoolingById(false, id!!)
                        dao.updateAmountCoolingById(1, id!!)
                    }
                }
            }.start()
        }


        val counterText = TextView(this.context)
        counterText.layoutParams = paramTableRow
        counterText.text = "${amount}"
        counterText.textSize = 16f
        counterText.setTextColor(0xFFFFFFFF.toInt())
        counterText.setPadding(50, 0, 0, 20)

        val minus = ImageButton(this.context)
        minus.layoutParams = paramTableRow
        minus.setBackgroundColor(0xFF6D7AC2.toInt())
        minus.setImageResource(R.drawable.minus)
        minus.setPadding(80, 32, 0 ,20)
        minus.setOnClickListener{
            if(amount > 1) {
                amount--
                counterText.text = "${amount}"
                Thread{
                    when(type){
                        "Motherboard" -> dao.updateAmountMotherboardById(amount, id!!)
                        "CPU" -> dao.updateAmountCoreById(amount, id!!)
                        "Videocard" -> dao.updateAmountVideocardById(amount, id!!)
                        "Ram" -> dao.updateAmountRamById(amount, id!!)
                        "Storage" -> dao.updateAmountStorageById(amount, id!!)
                        "Power" -> dao.updateAmountPowerById(amount, id!!)
                        "Cooling" -> dao.updateAmountCoolingById(amount, id!!)
                    }
                }.start()
            }
        }

        val plus = ImageButton(this.context)
        plus.layoutParams = paramTableRow
        plus.setBackgroundColor(0xFF6D7AC2.toInt())
        plus.setImageResource(R.drawable.plus)
        plus.setPadding(50, 20, 0 ,20)
        plus.setOnClickListener{
            amount++
            counterText.text = "${amount}"
            Thread{
                when(type){
                    "Motherboard" -> dao.updateAmountMotherboardById(amount, id!!)
                    "CPU" -> dao.updateAmountCoreById(amount, id!!)
                    "Videocard" -> dao.updateAmountVideocardById(amount, id!!)
                    "Ram" -> dao.updateAmountRamById(amount, id!!)
                    "Storage" -> dao.updateAmountStorageById(amount, id!!)
                    "Power" -> dao.updateAmountPowerById(amount, id!!)
                    "Cooling" -> dao.updateAmountCoolingById(amount, id!!)

                }
            }.start()

        }

        tableRow2.addView(mark)
        tableRow2.addView(comparison)
        tableRow2.addView(trash)
        tableRow2.addView(minus)
        tableRow2.addView(counterText)
        tableRow2.addView(plus)

        tableLayout1.addView(tableRow1)
        tableLayout2.addView(tableRow2)

        grid.addView(tableLayout1)
        grid.addView(tableLayout2)

    }
}

