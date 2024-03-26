package com.example.pcstore

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    @Insert
    fun insertItem(item: MotherboardItem)

    @Query("SELECT * FROM Motherboard")
    fun getAllItemMotherboard():Flow<List<MotherboardItem>>

    @Query("SELECT * FROM Core")
    fun getAllItemCore():Flow<List<CoreItem>>

    @Query("SELECT * FROM Videocard")
    fun getAllItemVideocard():Flow<List<VideocardItem>>

    @Query("SELECT * FROM Ram")
    fun getAllItemRam():Flow<List<RamItem>>

    @Query("SELECT * FROM Storage")
    fun getAllItemStorage():Flow<List<StorageItem>>

    @Query("SELECT * FROM Power")
    fun getAllItemPower():Flow<List<PowerItem>>

    @Query("SELECT * FROM Cooling")
    fun getAllItemCooling():Flow<List<CoolingItem>>
    /*
    @Query("SELECT * FROM Motherboard WHERE favorites = TRUE")
    fun getFavoritesItem():Flow<List<MotherboardItem>>

    @Query("SELECT * FROM Motherboard WHERE comparison = TRUE")
    fun geComparisonsItem():Flow<List<MotherboardItem>>*/
    @Query("SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM motherboard where bucket = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM core where bucket = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM videocard where bucket = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM ram where bucket = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM storage where bucket = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM power where bucket = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM cooling where bucket = TRUE")
            fun getBucketsItems(): Flow<List<BucketItem>>

    @Query("SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM motherboard where favorites = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM core where favorites = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM videocard where favorites = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM ram where favorites = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM storage where favorites = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM power where favorites = TRUE\n" +
            "UNION\n" +
            "SELECT id as idItem, type, brand, name, price, favorites, comparison, bucket, amount FROM cooling where favorites = TRUE")
    fun getFavoritesItems(): Flow<List<BucketItem>>

    //Motherboard
    @Query("UPDATE motherboard SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketMotherboardById(userBucket: Boolean, userId: Int)

    @Query("UPDATE motherboard SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesMotherboardById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE motherboard SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonMotherboardById(userComparison: Boolean, userId: Int)

    @Query("UPDATE motherboard SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountMotherboardById(num: Int, userId: Int)

    //Core
    @Query("UPDATE core SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketCoreById(userBucket: Boolean, userId: Int)

    @Query("UPDATE core SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesCoreById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE core SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonCoreById(userComparison: Boolean, userId: Int)

    @Query("UPDATE core SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountCoreById(num: Int, userId: Int)

    //Videocard
    @Query("UPDATE videocard SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketVideocardById(userBucket: Boolean, userId: Int)

    @Query("UPDATE videocard SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesVideocardById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE videocard SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonVideocardById(userComparison: Boolean, userId: Int)

    @Query("UPDATE videocard SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountVideocardById(num: Int, userId: Int)

    //Ram
    @Query("UPDATE ram SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketRamById(userBucket: Boolean, userId: Int)

    @Query("UPDATE ram SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesRamById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE ram SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonRamById(userComparison: Boolean, userId: Int)

    @Query("UPDATE ram SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountRamById(num: Int, userId: Int)

    //Storage
    @Query("UPDATE storage SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketStorageById(userBucket: Boolean, userId: Int)

    @Query("UPDATE storage SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesStorageById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE storage SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonStorageById(userComparison: Boolean, userId: Int)

    @Query("UPDATE storage SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountStorageById(num: Int, userId: Int)

    //Power
    @Query("UPDATE power SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketPowerById(userBucket: Boolean, userId: Int)

    @Query("UPDATE power SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesPowerById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE power SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonPowerById(userComparison: Boolean, userId: Int)

    @Query("UPDATE power SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountPowerById(num: Int, userId: Int)

    //Cooling
    @Query("UPDATE cooling SET bucket = (:userBucket) WHERE  id IN (:userId)")
    fun updateBucketCoolingById(userBucket: Boolean, userId: Int)

    @Query("UPDATE cooling SET favorites = (:userFavorites) WHERE  id IN (:userId)")
    fun updateFavoritesCoolingById(userFavorites: Boolean, userId: Int)

    @Query("UPDATE cooling SET comparison = (:userComparison) WHERE  id IN (:userId)")
    fun updateComparisonCoolingById(userComparison: Boolean, userId: Int)

    @Query("UPDATE cooling SET amount = (:num) WHERE  id IN (:userId)")
    fun updateAmountCoolingById(num: Int, userId: Int)


    @Query("SELECT SUM(sum) AS TotalSum FROM (\n" +
            "  select price * amount as sum FROM motherboard WHERE bucket = true\n" +
            "  UNION ALL\n" +
            "  select price * amount as sum FROM core WHERE bucket = true\n" +
            "  UNION ALL\n" +
            "  select price * amount as sum FROM videocard WHERE bucket = true\n" +
            "  UNION ALL\n" +
            "  select price * amount as sum FROM ram WHERE bucket = true\n" +
            "  UNION ALL\n" +
            "  select price * amount as sum FROM storage WHERE bucket = true\n" +
            "  UNION ALL\n" +
            "  select price * amount as sum FROM power WHERE bucket = true\n" +
            "  UNION ALL\n" +
            "  select price * amount as sum FROM cooling WHERE bucket = true\n" +
            ") AS SubQuery;")
    fun sumForBucket(): Flow<Int>
}