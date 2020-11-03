package com.bridgetree.testingbabysteps.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bridgetree.testingbabysteps.R
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment: Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //We pass 'requireActivity()' instead of 'this 'to bind viewModel to out activity so that it survives
        // even if this fragment is destroyed, since we share the same viewModel with all of our fragments
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)


        fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment())
        }
    }
}