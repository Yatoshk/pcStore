package com.example.pcstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Motherboard")
data class MotherboardItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "brand")
    var brand: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "form")
    var form: String,
    @ColumnInfo(name = "socket")
    var soket: String,
    @ColumnInfo(name = "memory")
    var memory: Int,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "favorites")
    var favorites: Boolean,
    @ColumnInfo(name = "comparison")
    var comparison: Boolean,
    @ColumnInfo(name = "bucket")
    var bucket: Boolean,
    @ColumnInfo(name = "amount")
    var amount: Int,
    )

