package com.bridgetree.testingbabysteps.respositories

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import com.bridgetree.testingbabysteps.data.local.ShoppingItem
import com.bridgetree.testingbabysteps.data.remote.responses.ImageResponse
import com.bridgetree.testingbabysteps.other.Resource

//One of the S.O.L.I.D principle

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

}