package com.example.g56172.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.g56172.R
import com.example.g56172.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(HomeViewModel()::class.java)
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.daysButton.setOnClickListener(){
            findNavController().navigate(R.id.action_homesFragment_to_detailsFragment)
        }

        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_homesFragment_to_searchFragment)
        }

        viewModel.changeDegree("15")
        viewModel.changeResume("Shower")
        viewModel.changeDate("Fri. 5 Jun")
        viewModel.changePosition("Helsinki")
        viewModel.changeNumberMph("7")
        viewModel.changeNumberHumidity("84")
        viewModel.changeProgressBar( binding.progressBar,95)
        viewModel.changeNumberVisibility("6,4")
        viewModel.changeNumberAirPressure("998")
        viewModel.changeImageView(binding.imageView)

        return binding.root
    }

}