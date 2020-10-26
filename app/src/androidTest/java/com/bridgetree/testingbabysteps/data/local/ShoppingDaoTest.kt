package com.bridgetree.testingbabysteps.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.bridgetree.testingbabysteps.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//As per testing pyramid where there are 3 layers, base(unitTests), mid(integratedTests), top(uiTests)
//Here SmallTest annotation indicates that these are uniTests, similarly there are MediumTest & LargeTest annotation
@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class ShoppingDaoTest {

    //Make every line of code execute/run one after another
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao


    //Note that we are explicitly making the db run on the main thread,
    //we use "inMemoryDatabaseBuilder", so as not to create an actual db
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.shoppingDao()
    }


    @After
    fun tearDown() {
        database.close()
    }


    //getOrAwaitValue() is obtained by inserting a file provided by google "LiveDataUtilAndroidTest.kt"
    //in-order to get the actual list instead of LiveData,
    //It might be clear by now that we are explicitly running everything on the main thread.
    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }


    @Test

    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }


    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shoppingItem1 = ShoppingItem("name", 2, 10f, "url", id = 1)
        val shoppingItem2 = ShoppingItem("name", 4, 5.5f, "url", id = 2)
        val shoppingItem3 = ShoppingItem("name", 0, 100f, "url", id = 3)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        val totalPriceSum = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPriceSum).isEqualTo(2 * 10f + 4 * 5.5f)
    }


    @Test
    fun observeAllShoppingItems() = runBlockingTest {
        val shoppingItem1 = ShoppingItem("name1", 2, 10f, "url", id = 1)
        val shoppingItem2 = ShoppingItem("name2", 4, 5.5f, "url", id = 2)
        val shoppingItem3 = ShoppingItem("name3", 0, 100f, "url", id = 3)
        //val shoppingItem4 = ShoppingItem("name4", 12, 100f, "url", id = 4)
        var list = listOf<ShoppingItem>(shoppingItem1, shoppingItem2, shoppingItem3/*, shoppingItem4*/)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).containsExactlyElementsIn(list)
    }

}