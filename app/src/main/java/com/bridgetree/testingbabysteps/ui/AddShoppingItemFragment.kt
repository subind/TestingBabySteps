package com.bridgetree.testingbabysteps.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bridgetree.testingbabysteps.R

class AddShoppingItemFragment: Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //We pass 'requireActivity()' instead of 'this 'to bind viewModel to out activity so that it survives
        // even if this fragment is destroyed, since we share the same viewModel with all of our fragments
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
    }

}