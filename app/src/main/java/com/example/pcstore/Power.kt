package com.example.pcstore

import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.pcstore.databinding.PowerActivityBinding

class Power: AppCompatActivity() {
    lateinit var binding: PowerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PowerActivityBinding.inflate(layoutInflater)

        var db: DataBase = DataBase.getDatabasePower(this)


        val dao = db.getDao()

        db.getDao().getAllItemPower().asLiveData().observe(this) {
            val grid = findViewById<GridLayout>(R.id.grid_power)

            grid.columnCount = 1;
            var i = 0
            it.forEach {
                createListDynamically(i, grid, dao, it)
                i++
            }
        }


        setContentView(binding.root)
    }
    private fun createListDynamically(row: Int, grid: GridLayout, dao: ItemsDao, it: PowerItem) {

        val id = it.id
        val type = it.type
        val brand = it.brand
        val name = it.name
        val form = it.form
        val capacity = it.capacity
        val efficiency = it.efficiency
        val comparisonState = it.comparison
        val price = it.price
        val favoritesState: Boolean = it.favorites
        val bucketState: Boolean = it.bucket

        val paramGrid = GridLayout.LayoutParams()
        paramGrid.height = GridLayout.LayoutParams.WRAP_CONTENT
        paramGrid.width = GridLayout.LayoutParams.WRAP_CONTENT
        paramGrid.columnSpec = GridLayout.spec(0)
        paramGrid.rowSpec = GridLayout.spec(row)

        val tableLayout = TableLayout(this)

        tableLayout.layoutParams = paramGrid

        val paramTable = TableLayout.LayoutParams()
        paramTable.height = TableLayout.LayoutParams.MATCH_PARENT
        paramTable.width = TableLayout.LayoutParams.MATCH_PARENT

        var x = row % 2
        when (x) {
            0 -> tableLayout.setBackgroundColor(0xFF383E60.toInt())
            1 -> tableLayout.setBackgroundColor(0xFF4C568A.toInt())
        }

        val tableRow1 = TableRow(this)

        val tableRow2 = TableRow(this)

        val tableRow3 = TableRow(this)

        val paramTableRow = TableRow.LayoutParams()
        paramTableRow.height = TableLayout.LayoutParams.WRAP_CONTENT
        paramTableRow.width = TableLayout.LayoutParams.MATCH_PARENT
        paramTableRow.gravity = Gravity.LEFT

        val nameText = TextView(this)

        nameText.layoutParams = paramTableRow
        nameText.text = "${type}\n${brand} ${name}\n"
        nameText.setTextColor(0xFFFFFFFF.toInt())
        nameText.setPadding(50, 50, 0, 0)

        tableRow1.addView(nameText)

        val pic = ImageView(this)
        pic.setImageResource(R.drawable.powerunit2)
        pic.setPadding(50, 15, 0, 0)
        pic.layoutParams = paramTableRow

        val infoText = TextView(this)
        infoText.layoutParams = paramTableRow
        infoText.text = "Форм-фактор: ${form}\n\nМощность: ${capacity} Вт\n\nКПД: ${efficiency}%"
        infoText.setPadding(50, 15, 0, 0)
        infoText.setTextColor(0xFFFFFFFF.toInt())

        tableRow2.addView(pic)
        tableRow2.addView(infoText)

        val mark = ImageButton(this)
        mark.layoutParams = paramTableRow
        when (x) {
            0 -> mark.setBackgroundColor(0xFF383E60.toInt())
            1 -> mark.setBackgroundColor(0xFF4C568A.toInt())
        }
        when (favoritesState) {
            false -> mark.setImageResource(R.drawable.bookmark_nonactive)
            true -> mark.setImageResource(R.drawable.bookmark_active)
        }
        mark.setPadding(50, 50, 0, 50)

        mark.setOnClickListener {
            when (favoritesState) {
                true -> {
                    mark.setImageResource(R.drawable.bookmark_nonactive)
                    Thread {
                        dao.updateFavoritesPowerById(false, id!!)
                    }.start()
                }

                false -> {
                    mark.setImageResource(R.drawable.bookmark_active)
                    Thread {
                        dao.updateFavoritesPowerById(true, id!!)
                    }.start()
                }
            }
        }

        val comparison = ImageButton(this)
        comparison.layoutParams = paramTableRow
        when (x) {
            0 -> comparison.setBackgroundColor(0xFF383E60.toInt())
            1 -> comparison.setBackgroundColor(0xFF4C568A.toInt())
        }
        when (comparisonState) {
            false -> comparison.setImageResource(R.drawable.comparision)
            true -> comparison.setImageResource(R.drawable.comparision_active)
        }
        comparison.setPadding(50, 50, 0, 50)
        comparison.setOnClickListener {
            when (comparisonState) {
                true -> {
                    comparison.setImageResource(R.drawable.comparision)
                    Thread {
                        dao.updateComparisonPowerById(false, id!!)
                    }.start()
                }

                false -> {
                    comparison.setImageResource(R.drawable.comparision_active)
                    Thread {
                        dao.updateComparisonPowerById(true, id!!)
                    }.start()
                }
            }
        }
        val busket = ImageButton(this)
        busket.layoutParams = paramTableRow
        when (x) {
            0 -> busket.setBackgroundColor(0xFF383E60.toInt())
            1 -> busket.setBackgroundColor(0xFF4C568A.toInt())
        }
        when (bucketState) {
            false -> busket.setImageResource(R.drawable.archive_nonactive)
            true -> busket.setImageResource(R.drawable.archive_active)
        }
        busket.setPadding(50, 50, 0, 50)
        busket.setOnClickListener {
            when (bucketState) {
                true -> {
                    busket.setImageResource(R.drawable.archive_nonactive)
                    Thread {
                        dao.updateBucketPowerById(false, id!!)
                    }.start()
                }

                false -> {
                    busket.setImageResource(R.drawable.archive_active)
                    Thread {
                        dao.updateBucketPowerById(true, id!!)
                    }.start()
                }
            }
        }
        val arrow = ImageButton(this)
        arrow.layoutParams = paramTableRow
        when (x) {
            0 -> arrow.setBackgroundColor(0xFF383E60.toInt())
            1 -> arrow.setBackgroundColor(0xFF4C568A.toInt())
        }
        arrow.setImageResource(R.drawable.arrow_right_gray)
        arrow.setPadding(50, 60, 0, 50)

        arrow.setOnClickListener {
            arrow.setImageResource(R.drawable.arrow_right)
        }

        val priceText = TextView(this)
        priceText.layoutParams = paramTableRow
        priceText.text = "${price}p"
        priceText.textSize = 18f
        priceText.setTextColor(0xFFFFFFFF.toInt())
        priceText.setPadding(50, 40, 0, 50)


        tableRow3.addView(mark)
        tableRow3.addView(comparison)
        tableRow3.addView(busket)
        tableRow3.addView(arrow)
        tableRow3.addView(priceText)

        tableLayout.addView(tableRow1)
        tableLayout.addView(tableRow2)
        tableLayout.addView(tableRow3)

        grid.addView(tableLayout)

    }
}