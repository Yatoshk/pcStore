package com.example.pcstore

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pcstore.ui.theme.Bucket
import com.example.pcstore.ui.theme.Favorites

@Database (entities = [MotherboardItem::class, CoreItem::class, VideocardItem::class, RamItem::class, StorageItem::class, PowerItem::class, CoolingItem::class, BucketItem::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): ItemsDao
    companion object{
        fun getDatabaseMotherboard(context: Motherboard): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }

        fun getDatabaseCore(context: Core): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }

        fun getDatabaseVideocard(context: Videocard): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }

        fun getDatabaseRam(context: Ram): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }

        fun getDatabaseStorage(context: Storage): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }
        fun getDatabasePower(context: Power): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }
        fun getDatabaseCooling(context: Cooling): DataBase
        {
            return Room.databaseBuilder(context.applicationContext, DataBase::class.java, "store.db").build()
        }

        fun getDatabaseBucket(context: Bucket): DataBase
        {
            return Room.databaseBuilder(context.requireContext(), DataBase::class.java, "store.db").build()
        }
        fun getDatabaseFavorites(context: Favorites): DataBase
        {
            return Room.databaseBuilder(context.requireContext(), DataBase::class.java, "store.db").build()
        }
    }
}