package com.example.g56172.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.api.Days
import com.example.g56172.api.WeatherFiveDays
import com.example.g56172.screens.home.HomeViewModel.Companion.dateTransform
import com.example.g56172.screens.home.HomeViewModel.Companion.toCelsius
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailsViewModel : ViewModel() {

    var days1Text = MutableLiveData<String>()
    var days1Resume = MutableLiveData<String>()
    var days1Max = MutableLiveData<String>()
    var days1Min = MutableLiveData<String>()
    var days2Text = MutableLiveData<String>()
    var days2Resume = MutableLiveData<String>()
    var days2Max = MutableLiveData<String>()
    var days2Min = MutableLiveData<String>()
    var days3Text = MutableLiveData<String>()
    var days3Resume = MutableLiveData<String>()
    var days3Max = MutableLiveData<String>()
    var days3Min = MutableLiveData<String>()
    var days4Text = MutableLiveData<String>()
    var days4Resume = MutableLiveData<String>()
    var days4Max = MutableLiveData<String>()
    var days4Min = MutableLiveData<String>()
    var days5Text = MutableLiveData<String>()
    var days5Resume = MutableLiveData<String>()
    var days5Max = MutableLiveData<String>()
    var days5Min = MutableLiveData<String>()

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
        this.days1Text.value = days1Text
        this.days1Max.value = days1Max
        this.days1Min.value = days1Min
        this.days1Resume.value = days1Resume
    }

    fun changeDays2Text(
        days2Text: String,
        days2Max: String,
        days2Min: String,
        days2Resume: String
    ) {
        this.days2Text.value = days2Text
        this.days2Max.value = days2Max
        this.days2Min.value = days2Min
        this.days2Resume.value = days2Resume
    }

    fun changeDays3Text(
        days3Text: String,
        days3Max: String,
        days3Min: String,
        days3Resume: String
    ) {
        this.days3Text.value = days3Text
        this.days3Max.value = days3Max
        this.days3Min.value = days3Min
        this.days3Resume.value = days3Resume
    }

    fun changeDays4Text(
        days4Text: String,
        days4Max: String,
        days4Min: String,
        days4Resume: String
    ) {
        this.days4Text.value = days4Text
        this.days4Max.value = days4Max
        this.days4Min.value = days4Min
        this.days4Resume.value = days4Resume
    }

    fun changeDays5Text(
        days5Text: String,
        days5Max: String,
        days5Min: String,
        days5Resume: String
    ) {
        this.days5Text.value = days5Text
        this.days5Max.value = days5Max
        this.days5Min.value = days5Min
        this.days5Resume.value = days5Resume
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