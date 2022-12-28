package com.example.g56172.screens.details

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.R
import com.example.g56172.api.Days
import com.example.g56172.api.WeatherFiveDays
import com.example.g56172.screens.home.HomeFragment
import com.example.g56172.screens.home.HomeFragment.Weather.weather
import com.example.g56172.screens.home.HomeViewModel
import com.example.g56172.screens.home.HomeViewModel.Companion.dateTransform
import com.example.g56172.screens.home.HomeViewModel.Companion.toCelsius
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DetailsViewModel : ViewModel() {

    var _days1Text = MutableLiveData<String>()
    var _days1Resume = MutableLiveData<String>()
    var _days1Max = MutableLiveData<String>()
    var _days1Min = MutableLiveData<String>()
    var _days2Text = MutableLiveData<String>()
    var _days2Resume = MutableLiveData<String>()
    var _days2Max = MutableLiveData<String>()
    var _days2Min = MutableLiveData<String>()
    var _days3Text = MutableLiveData<String>()
    var _days3Resume = MutableLiveData<String>()
    var _days3Max = MutableLiveData<String>()
    var _days3Min = MutableLiveData<String>()
    var _days4Text = MutableLiveData<String>()
    var _days4Resume = MutableLiveData<String>()
    var _days4Max = MutableLiveData<String>()
    var _days4Min = MutableLiveData<String>()
    var _days5Text = MutableLiveData<String>()
    var _days5Resume = MutableLiveData<String>()
    var _days5Max = MutableLiveData<String>()
    var _days5Min = MutableLiveData<String>()

    fun meanMinDailyTemperature(days : List<Days>, dateText: String): String {
        var totalTempOfDay = 0.0
        var numberOfTempOfDay = 0
        for (i in 0..days.size-1){
            if(dateText == days.get(i).dt_txt.split(' ')[0] ){
                totalTempOfDay+=days.get(i).main.temp_min
                numberOfTempOfDay++
            }
        }
        return toCelsius(totalTempOfDay/numberOfTempOfDay)
    }

    fun meanMaxDailyTemperature(days : List<Days>,dateText: String): String {
        var totalTempOfDay = 0.0
        var numberOfTempOfDay = 0
        for (i in 0..days.size-1){
            if(dateText == days.get(i).dt_txt.split(' ')[0] ){
                totalTempOfDay+=days.get(i).main.temp_max
                numberOfTempOfDay++
            }
        }
        return toCelsius(totalTempOfDay/numberOfTempOfDay)
    }
    fun changeDays1Text(
        days1Text: String,
        days1Max: String,
        days1Min: String,
        days1Resume: String
    ) {
        _days1Text.value = days1Text
        _days1Max.value = days1Max
        _days1Min.value = days1Min
        _days1Resume.value = days1Resume
    }

    fun changeDays2Text(
        days2Text: String,
        days2Max: String,
        days2Min: String,
        days2Resume: String
    ) {
        _days2Text.value = days2Text
        _days2Max.value = days2Max
        _days2Min.value = days2Min
        _days2Resume.value = days2Resume
    }

    fun changeDays3Text(
        days3Text: String,
        days3Max: String,
        days3Min: String,
        days3Resume: String
    ) {
        _days3Text.value = days3Text
        _days3Max.value = days3Max
        _days3Min.value = days3Min
        _days3Resume.value = days3Resume
    }

    fun changeDays4Text(
        days4Text: String,
        days4Max: String,
        days4Min: String,
        days4Resume: String
    ) {
        _days4Text.value = days4Text
        _days4Max.value = days4Max
        _days4Min.value = days4Min
        _days4Resume.value = days4Resume
    }

    fun changeDays5Text(
        days5Text: String,
        days5Max: String,
        days5Min: String,
        days5Resume: String
    ) {
        _days5Text.value = days5Text
        _days5Max.value = days5Max
        _days5Min.value = days5Min
        _days5Resume.value = days5Resume
    }

    fun updateAll(myWeater: WeatherFiveDays) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = current.format(formatter)
        var weatherList = myWeater.list
        var beginNewDaysIndex = -1
        if (weatherList.size != 0) {
            var j = 0
            while (beginNewDaysIndex == -1) {
                if (weatherList.get(j).dt_txt.split(' ')[0] != formattedDate.toString()) {
                    beginNewDaysIndex = j
                }
                j++
            }
        }
        var indexDay = 0
        for (i in beginNewDaysIndex..weatherList.size step 8) {
            var day = weatherList.get(i)
            when (indexDay) {
                0 -> changeDays1Text(
                    dateTransform(day.dt_txt),
                    meanMinDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    meanMaxDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    day.weather.get(0).main
                )
                1 -> changeDays2Text(
                    dateTransform(day.dt_txt),
                    meanMinDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    meanMaxDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    day.weather.get(0).main
                )
                2 -> changeDays3Text(
                    dateTransform(day.dt_txt),
                    meanMinDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    meanMaxDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    day.weather.get(0).main
                )
                3 -> changeDays4Text(
                    dateTransform(day.dt_txt),
                    meanMinDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    meanMaxDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    day.weather.get(0).main
                )
                4 -> changeDays5Text(
                    dateTransform(day.dt_txt),
                    meanMinDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    meanMaxDailyTemperature(weatherList,day.dt_txt.split(' ')[0]),
                    day.weather.get(0).main
                )
            }
            indexDay++
        }

    }

}