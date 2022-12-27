package com.example.g56172.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.g56172.R
import com.example.g56172.api.WeatherFiveDays
import com.example.g56172.databinding.FragmentDetailsBinding
import com.example.g56172.screens.home.HomeFragment
import com.example.g56172.screens.home.HomeFragment.Weather.weather

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


        viewModel._days1Resume.observe(viewLifecycleOwner) { resumeWeather ->
            changeImageView(resumeWeather,binding.days1Image)
        }
        viewModel._days2Resume.observe(viewLifecycleOwner) { resumeWeather ->
            changeImageView(resumeWeather,binding.days2Image)
        }
        viewModel._days3Resume.observe(viewLifecycleOwner) { resumeWeather ->
            changeImageView(resumeWeather,binding.days3Image)
        }
        viewModel._days4Resume.observe(viewLifecycleOwner) { resumeWeather ->
            changeImageView(resumeWeather,binding.days4Image)
        }
        viewModel._days5Resume.observe(viewLifecycleOwner) { resumeWeather ->
            changeImageView(resumeWeather,binding.days5Image)
        }
        viewModel.updateAll(weather)
        return binding.root
    }

    fun changeImageView(resume : String, view: ImageView) {
        when (resume) {
            "Clear" -> view.setImageResource(R.drawable.clear)
            "Rain" -> view.setImageResource(R.drawable.light_rain)
            "Clouds" -> view.setImageResource(R.drawable.light_cloud)
            "Mist" -> view.setImageResource(R.drawable.heavy_cloud)
            "Snow" -> view.setImageResource(R.drawable.snow)
            "Thunderstorm" -> view.setImageResource(R.drawable.thunderstorm)
            "Sleet" -> view.setImageResource(R.drawable.sleet)
            "Shower" -> view.setImageResource(R.drawable.shower)
            "Hail" -> view.setImageResource(R.drawable.hail)
            else -> view.setImageResource(R.drawable.shower)
        }

    }
}