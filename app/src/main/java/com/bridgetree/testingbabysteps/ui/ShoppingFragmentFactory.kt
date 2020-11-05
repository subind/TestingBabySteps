package com.bridgetree.testingbabysteps.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bridgetree.testingbabysteps.adapters.ImageAdapter
import com.bridgetree.testingbabysteps.adapters.ShoppingItemAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

/**
 * Purpose : Inject dependencies via constructor into our fragments
 *
 * This is the preferred way when it comes to testing, that we inject dependencies in the constructor
 * & not as field injection, because this makes it more flexible & convenient
 */

class ShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingItemAdapter: ShoppingItemAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)

            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)

            ShoppingFragment::class.java.name -> ShoppingFragment(shoppingItemAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }

}