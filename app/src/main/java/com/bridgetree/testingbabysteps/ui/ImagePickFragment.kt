package com.bridgetree.testingbabysteps.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bridgetree.testingbabysteps.R
import com.bridgetree.testingbabysteps.adapters.ImageAdapter
import com.bridgetree.testingbabysteps.other.Constants.GRID_SPAN_COUNT
import kotlinx.android.synthetic.main.fragment_image_pick.*
import javax.inject.Inject

class ImagePickFragment @Inject constructor(
    public val imageAdapter: ImageAdapter
) : Fragment(R.layout.fragment_image_pick) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //We pass 'requireActivity()' instead of 'this 'to bind viewModel to out activity so that it survives
        // even if this fragment is destroyed, since we share the same viewModel with all of our fragments
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        setupRecyclerView()

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }

    private fun setupRecyclerView(){
        rvImages.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }

}