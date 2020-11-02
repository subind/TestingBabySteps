package com.bridgetree.testingbabysteps.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bridgetree.testingbabysteps.MainCoroutineRule
import com.bridgetree.testingbabysteps.getOrAwaitValueTest
import com.bridgetree.testingbabysteps.other.Constants
import com.bridgetree.testingbabysteps.other.Constants.TEST_IMG_URL
import com.bridgetree.testingbabysteps.other.Status
import com.bridgetree.testingbabysteps.respositories.DefaultShoppingRepository
import com.bridgetree.testingbabysteps.respositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup(){
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field returns error`(){
        viewModel.insertShoppingItem("Subind", "", "4.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert shopping item with too long name returns error`(){
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH + 1){
                append(i)
            }
        }
        viewModel.insertShoppingItem(string, "5", "4.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }



    @Test
    fun `insert shopping item with too high amount returns error`(){
        viewModel.insertShoppingItem("subind", "9999999999999999999999999", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }



    @Test
    fun `insert shopping item with valid input returns success`(){
        viewModel.insertShoppingItem("subind", "9", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }


    @Test
    fun `image url is empty after successful insert, returns true`(){
        viewModel.insertShoppingItem("subind", "9", "3.0")

        val value = viewModel.curImageUrl.getOrAwaitValueTest()
        assertThat(value).isEmpty()
    }


    @Test
    fun `set url data is also same in liveData, returns true`(){
        viewModel.setCurImageUrl(TEST_IMG_URL)

        val value = viewModel.curImageUrl.getOrAwaitValueTest()
        assertThat(value).isEqualTo(TEST_IMG_URL)
    }


}