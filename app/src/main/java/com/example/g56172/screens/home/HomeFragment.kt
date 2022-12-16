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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.g56172.R
import com.example.g56172.api.RetrofitApi
import com.example.g56172.api.WeatherFiveDays
import com.example.g56172.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : Fragment(), LocationListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    //    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    private var weather = MutableLiveData<WeatherFiveDays>()

    private var json = """
        {
          "cod": "200",
          "message": 0,
          "cnt": 40,
          "list": [
            {
              "dt": 1671138000,
              "main": {
                "temp": 269.27,
                "feels_like": 269.27,
                "temp_min": 269.27,
                "temp_max": 271.71,
                "pressure": 1009,
                "sea_level": 1009,
                "grnd_level": 1003,
                "humidity": 86,
                "temp_kf": -2.44
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02n"
                }
              ],
              "clouds": {
                "all": 20
              },
              "wind": {
                "speed": 0.86,
                "deg": 314,
                "gust": 1.13
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-15 21:00:00"
            },
            {
              "dt": 1671148800,
              "main": {
                "temp": 270.07,
                "feels_like": 270.07,
                "temp_min": 270.07,
                "temp_max": 271.68,
                "pressure": 1010,
                "sea_level": 1010,
                "grnd_level": 1003,
                "humidity": 86,
                "temp_kf": -1.61
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02n"
                }
              ],
              "clouds": {
                "all": 16
              },
              "wind": {
                "speed": 1.3,
                "deg": 204,
                "gust": 1.43
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-16 00:00:00"
            },
            {
              "dt": 1671159600,
              "main": {
                "temp": 271.38,
                "feels_like": 271.38,
                "temp_min": 271.38,
                "temp_max": 272.43,
                "pressure": 1011,
                "sea_level": 1011,
                "grnd_level": 1004,
                "humidity": 87,
                "temp_kf": -1.05
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03n"
                }
              ],
              "clouds": {
                "all": 49
              },
              "wind": {
                "speed": 1.32,
                "deg": 303,
                "gust": 1.36
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-16 03:00:00"
            },
            {
              "dt": 1671170400,
              "main": {
                "temp": 271.99,
                "feels_like": 268.78,
                "temp_min": 271.99,
                "temp_max": 271.99,
                "pressure": 1014,
                "sea_level": 1014,
                "grnd_level": 1006,
                "humidity": 94,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 79
              },
              "wind": {
                "speed": 2.48,
                "deg": 8,
                "gust": 4.87
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-16 06:00:00"
            },
            {
              "dt": 1671181200,
              "main": {
                "temp": 272.27,
                "feels_like": 269.64,
                "temp_min": 272.27,
                "temp_max": 272.27,
                "pressure": 1016,
                "sea_level": 1016,
                "grnd_level": 1009,
                "humidity": 88,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02d"
                }
              ],
              "clouds": {
                "all": 11
              },
              "wind": {
                "speed": 2.02,
                "deg": 28,
                "gust": 3.36
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-16 09:00:00"
            },
            {
              "dt": 1671192000,
              "main": {
                "temp": 274.77,
                "feels_like": 272.57,
                "temp_min": 274.77,
                "temp_max": 274.77,
                "pressure": 1017,
                "sea_level": 1017,
                "grnd_level": 1010,
                "humidity": 70,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 8
              },
              "wind": {
                "speed": 2.01,
                "deg": 28,
                "gust": 3.93
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-16 12:00:00"
            },
            {
              "dt": 1671202800,
              "main": {
                "temp": 273.01,
                "feels_like": 271.22,
                "temp_min": 273.01,
                "temp_max": 273.01,
                "pressure": 1018,
                "sea_level": 1018,
                "grnd_level": 1011,
                "humidity": 78,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 3
              },
              "wind": {
                "speed": 1.51,
                "deg": 18,
                "gust": 1.51
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-16 15:00:00"
            },
            {
              "dt": 1671213600,
              "main": {
                "temp": 271.81,
                "feels_like": 271.81,
                "temp_min": 271.81,
                "temp_max": 271.81,
                "pressure": 1020,
                "sea_level": 1020,
                "grnd_level": 1013,
                "humidity": 82,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 0.5,
                "deg": 56,
                "gust": 0.59
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-16 18:00:00"
            },
            {
              "dt": 1671224400,
              "main": {
                "temp": 271.59,
                "feels_like": 271.59,
                "temp_min": 271.59,
                "temp_max": 271.59,
                "pressure": 1021,
                "sea_level": 1021,
                "grnd_level": 1014,
                "humidity": 82,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 0.69,
                "deg": 165,
                "gust": 0.72
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-16 21:00:00"
            },
            {
              "dt": 1671235200,
              "main": {
                "temp": 271.24,
                "feels_like": 271.24,
                "temp_min": 271.24,
                "temp_max": 271.24,
                "pressure": 1022,
                "sea_level": 1022,
                "grnd_level": 1015,
                "humidity": 83,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 1.3,
                "deg": 194,
                "gust": 1.26
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-17 00:00:00"
            },
            {
              "dt": 1671246000,
              "main": {
                "temp": 270.8,
                "feels_like": 268.15,
                "temp_min": 270.8,
                "temp_max": 270.8,
                "pressure": 1023,
                "sea_level": 1023,
                "grnd_level": 1016,
                "humidity": 84,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 1.86,
                "deg": 184,
                "gust": 1.8
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-17 03:00:00"
            },
            {
              "dt": 1671256800,
              "main": {
                "temp": 270.39,
                "feels_like": 267.24,
                "temp_min": 270.39,
                "temp_max": 270.39,
                "pressure": 1024,
                "sea_level": 1024,
                "grnd_level": 1017,
                "humidity": 84,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 2.18,
                "deg": 189,
                "gust": 2.24
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-17 06:00:00"
            },
            {
              "dt": 1671267600,
              "main": {
                "temp": 271.03,
                "feels_like": 267.97,
                "temp_min": 271.03,
                "temp_max": 271.03,
                "pressure": 1025,
                "sea_level": 1025,
                "grnd_level": 1018,
                "humidity": 76,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 10
              },
              "wind": {
                "speed": 2.2,
                "deg": 180,
                "gust": 3
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-17 09:00:00"
            },
            {
              "dt": 1671278400,
              "main": {
                "temp": 273.82,
                "feels_like": 271.25,
                "temp_min": 273.82,
                "temp_max": 273.82,
                "pressure": 1025,
                "sea_level": 1025,
                "grnd_level": 1018,
                "humidity": 57,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03d"
                }
              ],
              "clouds": {
                "all": 30
              },
              "wind": {
                "speed": 2.19,
                "deg": 178,
                "gust": 3.11
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-17 12:00:00"
            },
            {
              "dt": 1671289200,
              "main": {
                "temp": 272.44,
                "feels_like": 269.98,
                "temp_min": 272.44,
                "temp_max": 272.44,
                "pressure": 1025,
                "sea_level": 1025,
                "grnd_level": 1018,
                "humidity": 58,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 8
              },
              "wind": {
                "speed": 1.91,
                "deg": 160,
                "gust": 1.91
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-17 15:00:00"
            },
            {
              "dt": 1671300000,
              "main": {
                "temp": 271.17,
                "feels_like": 268.07,
                "temp_min": 271.17,
                "temp_max": 271.17,
                "pressure": 1026,
                "sea_level": 1026,
                "grnd_level": 1019,
                "humidity": 53,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 9
              },
              "wind": {
                "speed": 2.25,
                "deg": 156,
                "gust": 2.31
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-17 18:00:00"
            },
            {
              "dt": 1671310800,
              "main": {
                "temp": 271.27,
                "feels_like": 268.04,
                "temp_min": 271.27,
                "temp_max": 271.27,
                "pressure": 1027,
                "sea_level": 1027,
                "grnd_level": 1020,
                "humidity": 51,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 68
              },
              "wind": {
                "speed": 2.38,
                "deg": 173,
                "gust": 2.91
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-17 21:00:00"
            },
            {
              "dt": 1671321600,
              "main": {
                "temp": 270.82,
                "feels_like": 267.78,
                "temp_min": 270.82,
                "temp_max": 270.82,
                "pressure": 1027,
                "sea_level": 1027,
                "grnd_level": 1020,
                "humidity": 59,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 84
              },
              "wind": {
                "speed": 2.15,
                "deg": 172,
                "gust": 2.26
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-18 00:00:00"
            },
            {
              "dt": 1671332400,
              "main": {
                "temp": 270.4,
                "feels_like": 267.02,
                "temp_min": 270.4,
                "temp_max": 270.4,
                "pressure": 1027,
                "sea_level": 1027,
                "grnd_level": 1020,
                "humidity": 54,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03n"
                }
              ],
              "clouds": {
                "all": 35
              },
              "wind": {
                "speed": 2.37,
                "deg": 140,
                "gust": 3.16
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-18 03:00:00"
            },
            {
              "dt": 1671343200,
              "main": {
                "temp": 270.22,
                "feels_like": 266.15,
                "temp_min": 270.22,
                "temp_max": 270.22,
                "pressure": 1026,
                "sea_level": 1026,
                "grnd_level": 1019,
                "humidity": 45,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03n"
                }
              ],
              "clouds": {
                "all": 39
              },
              "wind": {
                "speed": 2.98,
                "deg": 137,
                "gust": 5.67
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-18 06:00:00"
            },
            {
              "dt": 1671354000,
              "main": {
                "temp": 270.94,
                "feels_like": 266.76,
                "temp_min": 270.94,
                "temp_max": 270.94,
                "pressure": 1026,
                "sea_level": 1026,
                "grnd_level": 1018,
                "humidity": 41,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 98
              },
              "wind": {
                "speed": 3.26,
                "deg": 144,
                "gust": 8.88
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-18 09:00:00"
            },
            {
              "dt": 1671364800,
              "main": {
                "temp": 273.4,
                "feels_like": 269.8,
                "temp_min": 273.4,
                "temp_max": 273.4,
                "pressure": 1024,
                "sea_level": 1024,
                "grnd_level": 1017,
                "humidity": 37,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 99
              },
              "wind": {
                "speed": 3.17,
                "deg": 145,
                "gust": 9.84
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-18 12:00:00"
            },
            {
              "dt": 1671375600,
              "main": {
                "temp": 273.65,
                "feels_like": 269.75,
                "temp_min": 273.65,
                "temp_max": 273.65,
                "pressure": 1022,
                "sea_level": 1022,
                "grnd_level": 1015,
                "humidity": 43,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 3.62,
                "deg": 157,
                "gust": 10.14
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-18 15:00:00"
            },
            {
              "dt": 1671386400,
              "main": {
                "temp": 273.98,
                "feels_like": 269.61,
                "temp_min": 273.98,
                "temp_max": 273.98,
                "pressure": 1021,
                "sea_level": 1021,
                "grnd_level": 1014,
                "humidity": 53,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 4.43,
                "deg": 165,
                "gust": 11.92
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-18 18:00:00"
            },
            {
              "dt": 1671397200,
              "main": {
                "temp": 274.82,
                "feels_like": 270.38,
                "temp_min": 274.82,
                "temp_max": 274.82,
                "pressure": 1019,
                "sea_level": 1019,
                "grnd_level": 1012,
                "humidity": 58,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 4.89,
                "deg": 165,
                "gust": 13.38
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-18 21:00:00"
            },
            {
              "dt": 1671408000,
              "main": {
                "temp": 275.46,
                "feels_like": 270.67,
                "temp_min": 275.46,
                "temp_max": 275.46,
                "pressure": 1017,
                "sea_level": 1017,
                "grnd_level": 1010,
                "humidity": 68,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 5.92,
                "deg": 162,
                "gust": 15.12
              },
              "visibility": 10000,
              "pop": 0.37,
              "rain": {
                "3h": 0.47
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-19 00:00:00"
            },
            {
              "dt": 1671418800,
              "main": {
                "temp": 276.92,
                "feels_like": 271.78,
                "temp_min": 276.92,
                "temp_max": 276.92,
                "pressure": 1016,
                "sea_level": 1016,
                "grnd_level": 1008,
                "humidity": 89,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.85,
                "deg": 189,
                "gust": 16.92
              },
              "visibility": 10000,
              "pop": 0.7,
              "rain": {
                "3h": 0.28
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-19 03:00:00"
            },
            {
              "dt": 1671429600,
              "main": {
                "temp": 278.13,
                "feels_like": 273.28,
                "temp_min": 278.13,
                "temp_max": 278.13,
                "pressure": 1015,
                "sea_level": 1015,
                "grnd_level": 1008,
                "humidity": 92,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 8.04,
                "deg": 195,
                "gust": 17.21
              },
              "visibility": 10000,
              "pop": 0.66,
              "rain": {
                "3h": 0.18
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-19 06:00:00"
            },
            {
              "dt": 1671440400,
              "main": {
                "temp": 279.2,
                "feels_like": 274.64,
                "temp_min": 279.2,
                "temp_max": 279.2,
                "pressure": 1015,
                "sea_level": 1015,
                "grnd_level": 1008,
                "humidity": 93,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 8.16,
                "deg": 197,
                "gust": 18.33
              },
              "visibility": 10000,
              "pop": 1,
              "rain": {
                "3h": 1.36
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-19 09:00:00"
            },
            {
              "dt": 1671451200,
              "main": {
                "temp": 280.16,
                "feels_like": 275.88,
                "temp_min": 280.16,
                "temp_max": 280.16,
                "pressure": 1015,
                "sea_level": 1015,
                "grnd_level": 1008,
                "humidity": 90,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 8.19,
                "deg": 201,
                "gust": 18.54
              },
              "visibility": 10000,
              "pop": 1,
              "rain": {
                "3h": 0.54
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-19 12:00:00"
            },
            {
              "dt": 1671462000,
              "main": {
                "temp": 280.27,
                "feels_like": 276.27,
                "temp_min": 280.27,
                "temp_max": 280.27,
                "pressure": 1014,
                "sea_level": 1014,
                "grnd_level": 1007,
                "humidity": 83,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.37,
                "deg": 197,
                "gust": 17.52
              },
              "visibility": 10000,
              "pop": 0.34,
              "rain": {
                "3h": 0.21
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-19 15:00:00"
            },
            {
              "dt": 1671472800,
              "main": {
                "temp": 280.6,
                "feels_like": 276.68,
                "temp_min": 280.6,
                "temp_max": 280.6,
                "pressure": 1014,
                "sea_level": 1014,
                "grnd_level": 1007,
                "humidity": 87,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.42,
                "deg": 195,
                "gust": 17.77
              },
              "visibility": 10000,
              "pop": 0.7,
              "rain": {
                "3h": 1.09
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-19 18:00:00"
            },
            {
              "dt": 1671483600,
              "main": {
                "temp": 281.12,
                "feels_like": 277.26,
                "temp_min": 281.12,
                "temp_max": 281.12,
                "pressure": 1014,
                "sea_level": 1014,
                "grnd_level": 1007,
                "humidity": 86,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.74,
                "deg": 193,
                "gust": 19.07
              },
              "visibility": 10000,
              "pop": 0.58,
              "rain": {
                "3h": 0.43
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-19 21:00:00"
            },
            {
              "dt": 1671494400,
              "main": {
                "temp": 281.89,
                "feels_like": 278.1,
                "temp_min": 281.89,
                "temp_max": 281.89,
                "pressure": 1013,
                "sea_level": 1013,
                "grnd_level": 1006,
                "humidity": 86,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 8.31,
                "deg": 194,
                "gust": 17.57
              },
              "visibility": 10000,
              "pop": 0.81,
              "rain": {
                "3h": 0.75
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-20 00:00:00"
            },
            {
              "dt": 1671505200,
              "main": {
                "temp": 281.74,
                "feels_like": 278.19,
                "temp_min": 281.74,
                "temp_max": 281.74,
                "pressure": 1013,
                "sea_level": 1013,
                "grnd_level": 1006,
                "humidity": 85,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.29,
                "deg": 191,
                "gust": 16.67
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-20 03:00:00"
            },
            {
              "dt": 1671516000,
              "main": {
                "temp": 281.93,
                "feels_like": 278.39,
                "temp_min": 281.93,
                "temp_max": 281.93,
                "pressure": 1012,
                "sea_level": 1012,
                "grnd_level": 1005,
                "humidity": 81,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.46,
                "deg": 187,
                "gust": 17.62
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-20 06:00:00"
            },
            {
              "dt": 1671526800,
              "main": {
                "temp": 282.32,
                "feels_like": 278.71,
                "temp_min": 282.32,
                "temp_max": 282.32,
                "pressure": 1012,
                "sea_level": 1012,
                "grnd_level": 1005,
                "humidity": 77,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 8.11,
                "deg": 185,
                "gust": 18.74
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-20 09:00:00"
            },
            {
              "dt": 1671537600,
              "main": {
                "temp": 283.19,
                "feels_like": 282.26,
                "temp_min": 283.19,
                "temp_max": 283.19,
                "pressure": 1011,
                "sea_level": 1011,
                "grnd_level": 1004,
                "humidity": 77,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.51,
                "deg": 187,
                "gust": 17.49
              },
              "visibility": 10000,
              "pop": 0,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-20 12:00:00"
            },
            {
              "dt": 1671548400,
              "main": {
                "temp": 283.46,
                "feels_like": 282.58,
                "temp_min": 283.46,
                "temp_max": 283.46,
                "pressure": 1009,
                "sea_level": 1009,
                "grnd_level": 1002,
                "humidity": 78,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 7.42,
                "deg": 190,
                "gust": 16.39
              },
              "visibility": 10000,
              "pop": 0.25,
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2022-12-20 15:00:00"
            },
            {
              "dt": 1671559200,
              "main": {
                "temp": 283.78,
                "feels_like": 283.04,
                "temp_min": 283.78,
                "temp_max": 283.78,
                "pressure": 1009,
                "sea_level": 1009,
                "grnd_level": 1002,
                "humidity": 82,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10n"
                }
              ],
              "clouds": {
                "all": 100
              },
              "wind": {
                "speed": 8.54,
                "deg": 201,
                "gust": 17.83
              },
              "visibility": 10000,
              "pop": 0.51,
              "rain": {
                "3h": 0.5
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2022-12-20 18:00:00"
            }
          ],
          "city": {
            "id": 2799925,
            "name": "Cureghem",
            "coord": {
              "lat": 50.8333,
              "lon": 4.35
            },
            "country": "BE",
            "population": 2000,
            "timezone": 3600,
            "sunrise": 1671089908,
            "sunset": 1671118620
          }
        }
    """.trimIndent()

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
        weather.observe(viewLifecycleOwner) { myWeather ->
            myWeather?.let {
                val daysWeather = myWeather.list.get(0).weather.get(0)
                Log.i("WeatherApi", daysWeather.description)
                viewModel._resumeText.value = daysWeather.description
                val celsius = myWeather.list.get(0).main.temp - 273.15
                viewModel._degreeText.value = celsius.toInt().toString()
                viewModel._resumeText.value = daysWeather.main
                val date = myWeather.list.get(0).dt_txt
                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val dateTime: LocalDateTime = LocalDateTime.parse(date, formatter)
                val dateToInsert = dateTime.dayOfWeek.name.substring(0, 2).lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + ". " + dateTime.dayOfWeek.value + " " + dateTime.month.name.lowercase()
                    .substring(0, 3)
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                viewModel._dateText.value = dateToInsert
                viewModel._positionText.value = myWeather.city.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                viewModel._numberMphText.value = myWeather.list.get(0).wind.speed.toString()
                viewModel._windDirectionText.value = myWeather.list.get(0).wind.deg.toString() + "°"
                viewModel._numberHumidityText.value = myWeather.list.get(0).main.humidity.toString()
                changeProgressBar(myWeather.list.get(0).main.humidity)
                viewModel._numberVisibilityText.value = StringBuilder(
                    myWeather.list.get(0).visibility.toString().substring(0, 2)
                ).insert(1, ',').toString()
                viewModel._numberAirPressureText.value =
                    myWeather.list.get(0).main.pressure.toString()

            }
        }
        binding.geoButton.setOnClickListener {
            getLocation()
        }
        return binding.root
    }

    private fun getLocation() {
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
        }else {
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
                response.body()?.let {
                    weather.value = it
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