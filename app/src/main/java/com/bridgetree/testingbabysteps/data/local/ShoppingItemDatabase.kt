package com.bridgetree.testingbabysteps.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1)
abstract class ShoppingItemDatabase: RoomDatabase() {

    //Room will generate this for us
    abstract fun shoppingDao(): ShoppingDao
}