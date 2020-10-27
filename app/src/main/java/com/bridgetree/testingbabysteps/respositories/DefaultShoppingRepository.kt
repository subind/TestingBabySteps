package com.bridgetree.testingbabysteps.respositories

import androidx.lifecycle.LiveData
import com.bridgetree.testingbabysteps.data.local.ShoppingDao
import com.bridgetree.testingbabysteps.data.local.ShoppingItem
import com.bridgetree.testingbabysteps.data.remote.PixabayAPI
import com.bridgetree.testingbabysteps.data.remote.responses.ImageResponse
import com.bridgetree.testingbabysteps.other.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
): ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            }else{
                Resource.error("An unknown error occured", null)
            }
        }catch (e: Exception){
            Resource.error("Couldnt reach the server, check network connection", null)
        }
    }
}