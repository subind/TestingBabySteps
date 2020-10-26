package com.bridgetree.testingbabysteps

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceCompareTest {

    /**
     *  In this exercise we learned about @Before & @After annotation

        How does it help?
        If we instantiate our class to be tested, within every test method,
        for eg: let says we have 30 test methods, each of these methods have the
        line to instantiate our class, therefore this leads to 30 new lines of code within our class

        Solution:
        @Before annotation. @After annotation will be useful while working with Room database
        then we need to close the db after every test-case.
     *
     */

    private lateinit var resourceCompare: ResourceCompare

    @Before
    fun setUp() {
        resourceCompare = ResourceCompare()
    }

    @After
    fun tearDown(){

    }

    @Test
    fun stringResourceSameAsSuppliedString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "TestingBabySteps")
        assertThat(result).isTrue()
    }


    @Test
    fun stringResourceDifferentFromSuppliedString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "TestingAdultSteps")
        assertThat(result).isFalse()
    }

}