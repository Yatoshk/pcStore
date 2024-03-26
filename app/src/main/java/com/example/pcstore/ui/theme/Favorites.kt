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



class Favorites : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        var db: DataBase = DataBase.getDatabaseFavorites(this)

        val dao = db.getDao()

        val grid = view.findViewById<GridLayout>(R.id.grid_favorites)

        db.getDao().getFavoritesItems().asLiveData().observe(viewLifecycleOwner) { it ->
            grid.removeAllViews()
            var i: Int = 0
            var row:Int = 0
            var column: Int = 0
            it.forEach{
                if (column == 2)
                {
                    column = 0
                    row++
                }
                createListDynamically(column, row, grid, dao, it)
                i++
                column++
            }
        }

        return view
    }
    private fun createListDynamically(column: Int, row: Int, grid: GridLayout, dao: ItemsDao, it: BucketItem) {
        val id = it.idItem
        val type = it.type
        val brand = it.brand
        val name = it.name
        val comparisonState = it.comparison
        val price = it.price
        val favoritesState: Boolean = it.favorites
        var bucketState: Boolean = it.bucket

        val paramGrid = GridLayout.LayoutParams()
        paramGrid.height = GridLayout.LayoutParams.WRAP_CONTENT
        paramGrid.width = GridLayout.LayoutParams.WRAP_CONTENT
        paramGrid.columnSpec = GridLayout.spec(column)
        paramGrid.rowSpec = GridLayout.spec(row)
        paramGrid.setMargins(50, 15, 25 ,50)

        val tableLayout1 = TableLayout(this.context)
        tableLayout1.layoutParams = paramGrid

        val tableLayout2 = TableLayout(this.context)
        tableLayout2.layoutParams = paramGrid
        tableLayout2.setPadding(50,380,0,0)

        val tableLayout3 = TableLayout(this.context)
        tableLayout3.layoutParams = paramGrid
        tableLayout3.setPadding(50,360,0,0)

        val paramTable = TableLayout.LayoutParams()
        paramTable.height = TableLayout.LayoutParams.WRAP_CONTENT
        paramTable.width = TableLayout.LayoutParams.WRAP_CONTENT
        tableLayout1.setBackgroundResource(R.drawable.itemfavorite)

        val tableRow1 = TableRow(this.context)

        val tableRow2 = TableRow(this.context)

        val tableRow3 = TableRow(this.context)

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
        pic.setPadding(0, 0, 0, 340)

        val infoText = TextView(this.context)
        infoText.layoutParams = paramTableRow
        infoText.text = "${type}\n${brand} ${name}\n\n${price}p"
        infoText.setTextColor(0xFFFFFFFF.toInt())
        infoText.width = 370
        infoText.setPadding(0, 50, 0, 60)

        tableRow1.addView(pic)
        tableRow2.addView(infoText)

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
        val bucket = ImageButton(this.context)
        bucket.layoutParams = paramTableRow
        bucket.setBackgroundColor(0xFF6D7AC2.toInt())
        when (bucketState) {
            false -> bucket.setImageResource(R.drawable.archive_nonactive)
            true -> bucket.setImageResource(R.drawable.archive_active)
        }
        bucket.setPadding(60, 0, 0 ,20)
        bucket.setOnClickListener{
            when (bucketState) {
                true -> {
                    bucket.setImageResource(R.drawable.archive_nonactive)
                    Thread{
                        when(type){
                            "Motherboard" -> dao.updateBucketMotherboardById(false, id!!)
                            "CPU" -> dao.updateBucketCoreById(false, id!!)
                            "Videocard" -> dao.updateBucketVideocardById(false, id!!)
                            "Ram" -> dao.updateBucketRamById(false, id!!)
                            "Storage" -> dao.updateBucketStorageById(false, id!!)
                            "Power" -> dao.updateBucketPowerById(false, id!!)
                            "Cooling" -> dao.updateBucketCoolingById(false, id!!)
                        }
                    }.start()
                }
                false -> {
                    bucket.setImageResource(R.drawable.archive_active)
                    Thread{
                        when(type){
                            "Motherboard" -> dao.updateBucketMotherboardById(true, id!!)
                            "CPU" -> dao.updateBucketCoreById(true, id!!)
                            "Videocard" -> dao.updateBucketVideocardById(true, id!!)
                            "Ram" -> dao.updateBucketRamById(true, id!!)
                            "Storage" -> dao.updateBucketStorageById(true, id!!)
                            "Power" -> dao.updateBucketPowerById(true, id!!)
                            "Cooling" -> dao.updateBucketCoolingById(true, id!!)
                        }
                    }.start()
                }
            }
        }


        tableRow3.addView(mark)
        tableRow3.addView(comparison)
        tableRow3.addView(bucket)

        tableLayout1.addView(tableRow1)
        tableLayout2.addView(tableRow2)
        tableLayout3.addView(tableRow3)

        grid.addView(tableLayout1)
        grid.addView(tableLayout2)
        grid.addView(tableLayout3)

    }

}