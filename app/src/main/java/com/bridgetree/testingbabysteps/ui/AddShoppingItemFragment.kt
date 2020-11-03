package com.bridgetree.testingbabysteps.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bridgetree.testingbabysteps.R
import com.bridgetree.testingbabysteps.other.Status
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import javax.inject.Inject

class AddShoppingItemFragment @Inject constructor(
    val glide: RequestManager
): Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //We pass 'requireActivity()' instead of 'this 'to bind viewModel to out activity so that it survives
        // even if this fragment is destroyed, since we share the same viewModel with all of our fragments
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        subscribeToObservers()

        btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                etShoppingItemName.text.toString(),
                etShoppingItemAmount.text.toString(),
                etShoppingItemPrice.text.toString()
            )
        }

        ivShoppingImage.setOnClickListener {
            findNavController().navigate(AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment())
        }
        
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }

    private fun subscribeToObservers(){
        viewModel.curImageUrl.observe(viewLifecycleOwner, Observer {
            glide.load(it).into(ivShoppingImage)
        })
        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {result ->
                when(result.status){
                    Status.SUCCESS -> {
                        Snackbar.make(requireActivity().rootLayout, "Added Shopping Item", Snackbar.LENGTH_LONG).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {
                        Snackbar.make(requireActivity().rootLayout, result.message ?: "An unknown error occurred", Snackbar.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        /* No operation */
                    }
                }
            }
        })
    }

}