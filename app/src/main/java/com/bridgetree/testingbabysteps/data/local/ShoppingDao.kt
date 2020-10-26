package com.bridgetree.testingbabysteps.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    //Keyword 'suspend' is required to run it within  co-routine
    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    //LiveData runs asynchronously therefore its not required to make it a suspend function
    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM shopping_items")
    fun observeTotalPrice(): LiveData<Float>

}