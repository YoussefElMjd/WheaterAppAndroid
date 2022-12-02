package com.example.g56172.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(HomeViewModel(requireNotNull(this.activity).application)::class.java)
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.daysButton.setOnClickListener {
            findNavController().navigate(R.id.action_homesFragment_to_detailsFragment)
        }

        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_homesFragment_to_searchFragment)
        }
        viewModel.resumeText.observe(viewLifecycleOwner) { resumeWeather ->
            changeImageView(resumeWeather)
        }
        viewModel.numberHumidityText.observe(viewLifecycleOwner) { humidity ->
            changeProgressBar(humidity.toInt())
        }
        viewModel.changeDegree("15")
        viewModel.changeResume("Shower")
        viewModel.changeDate("Fri. 5 Jun")
        viewModel.changePosition("Helsinki")
        viewModel.changeNumberMph("7")
        viewModel.changeNumberHumidity("84")
        viewModel.changeNumberVisibility("6,4")
        viewModel.changeNumberAirPressure("998")

        return binding.root
    }
    fun changeProgressBar(progress: Int) {
        binding.progressBar.setProgress(progress)
    }

    fun changeImageView(resumeWeather : String) {
        when (resumeWeather) {
            "Clear" -> binding.imageDesc.setImageResource(R.drawable.clear)
            "Rain" -> binding.imageDesc.setImageResource(R.drawable.light_rain)
            "Clouds" -> binding.imageDesc.setImageResource(R.drawable.light_cloud)
            "Mist" -> binding.imageDesc.setImageResource(R.drawable.heavy_cloud)
            "Snow" -> binding.imageDesc.setImageResource(R.drawable.snow)
            "Thunderstorm" -> binding.imageDesc.setImageResource(R.drawable.thunderstorm)
            "Sleet" -> binding.imageDesc.setImageResource(R.drawable.sleet)
            "Shower" -> binding.imageDesc.setImageResource(R.drawable.shower)
            "Hail" -> binding.imageDesc.setImageResource(R.drawable.hail)
            else -> binding.imageDesc.setImageResource(R.drawable.shower)
        }

    }
}