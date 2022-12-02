package com.example.g56172.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.g56172.R
import com.example.g56172.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(DetailsViewModel()::class.java)
        binding.detailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        viewModel.changeDays1Text("Tomorrow", "16","11")
        viewModel.changeDays2Text("Sun. 7 Jun", "16","11")
        viewModel.changeDays3Text("Mon. 8 Jun", "16","11")
        viewModel.changeDays4Text("Tue. 9 Jun", "16","11")
        viewModel.changeDays5Text("Wed. 10 Jun", "16","11")
        return binding.root
    }

}