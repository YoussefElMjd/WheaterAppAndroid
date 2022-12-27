package com.example.g56172.screens.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.g56172.R
import com.example.g56172.api.RetrofitApi
import com.example.g56172.api.WeatherFiveDays
import com.example.g56172.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), LocationListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    companion object Weather{
        lateinit var weather : WeatherFiveDays

    }
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
        viewModel =
            ViewModelProvider(this).get(HomeViewModel(requireNotNull(this.activity).application)::class.java)
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
        viewModel.weather.observe(viewLifecycleOwner) { myWeather ->
            myWeather?.let {
                viewModel.updateViewWithApiVar(myWeather)
                changeProgressBar(myWeather.list.get(0).main.humidity)
            }
        }
        binding.geoButton.setOnClickListener {
            getLocation()
        }
        getLocation()
        return binding.root
    }


    fun changeProgressBar(progress: Int) {
        binding.progressBar.setProgress(progress)
    }

    fun changeImageView(resumeWeather: String) {
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

    fun getLocation() {
        locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        Log.i(
            "GeoLocation",
            "Latitude: " + location.latitude + " , Longitude: " + location.longitude
        );

        val callBack: Callback<WeatherFiveDays> = object : Callback<WeatherFiveDays> {
            override fun onFailure(call: Call<WeatherFiveDays>, t: Throwable) {
                Toast.makeText(requireContext(), "Erreur API", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<WeatherFiveDays>,
                response: Response<WeatherFiveDays>
            ) {
                response.body()?.let { myWeather ->
                    viewModel.updateViewWithApiVar(myWeather)
                    weather = myWeather
                }
            }
        }

        val weatherCall: Call<WeatherFiveDays> =
            RetrofitApi.myHttpClient.getWeather(location.latitude, location.longitude)
        weatherCall.enqueue(callBack)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}