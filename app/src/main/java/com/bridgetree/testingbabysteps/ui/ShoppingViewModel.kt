package com.bridgetree.testingbabysteps.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bridgetree.testingbabysteps.data.local.ShoppingItem
import com.bridgetree.testingbabysteps.data.remote.responses.ImageResponse
import com.bridgetree.testingbabysteps.other.Constants
import com.bridgetree.testingbabysteps.other.Event
import com.bridgetree.testingbabysteps.other.Resource
import com.bridgetree.testingbabysteps.respositories.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
        private val repository: ShoppingRepository
): ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImageUrl(url: String){
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String){
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The name of the item should not"
                    + "exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }
        if(priceString.length > Constants.MAX_PRICE_LENGTH){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The name of the item should not"
                    + "exceed ${Constants.MAX_PRICE_LENGTH} characters", null)))
            return
        }
        val amount = try{
            amountString.toInt()
        }catch (e: Exception){
            _insertShoppingItemStatus.postValue(Event(Resource.error("Please enter a valid amount", null)))
            return
        }
        val shoppingItem = ShoppingItem(name, amount, priceString.toFloat(), _curImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurImageUrl("")
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }

    fun searchForImage(imageQuery: String){
        if(imageQuery.isEmpty()){
            return
        }
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }



}