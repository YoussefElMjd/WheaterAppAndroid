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
import com.example.g56172.databinding.FragmentHomeBinding

class HomeFragment : Fragment(),LocationListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationManager : LocationManager
    private val locationPermissionCode = 2
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
        viewModel.changeDegree("15")
        viewModel.changeResume("Shower")
        viewModel.changeDate("Fri. 5 Jun")
        viewModel.changePosition("Helsinki")
        viewModel.changeNumberMph("7")
        viewModel.changeNumberHumidity("84")
        viewModel.changeNumberVisibility("6,4")
        viewModel.changeNumberAirPressure("998")
//        activity?.let {
//            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(it)
//        }
        binding.geoButton.setOnClickListener {
            getLocation()
        }

        return binding.root
    }
    private fun getLocation() {
        locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }
    override fun onLocationChanged(location: Location) {

       Log.i("Location","Latitude: " + location.latitude + " , Longitude: " + location.longitude);
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
//    private fun getLastLocation() {
//
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            Toast.makeText(
//                requireContext(),
//                "You don't have the permission for position",
//                Toast.LENGTH_LONG
//            )
//            return
//        } else {
//
//            fusedLocationProviderClient.getLastLocation().addOnSuccessListener {
//                @Override
//                fun onSuccess(location: Location) {
//                    if (location != null) {
//                        var geocoder = Geocoder(requireContext(), Locale.getDefault())
//                        var addresse =
//                            geocoder.getFromLocation(
//                                location.getLatitude(),
//                                location.getLongitude(),
//                                1
//                            )
//                        if (addresse != null) {
//                            Log.i("Location", addresse.get(0).latitude.toString())
//                        }
//                    }
//                }
//            }
//        }
//    }
//

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
}