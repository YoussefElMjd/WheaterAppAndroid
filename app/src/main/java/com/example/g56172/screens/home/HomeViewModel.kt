package com.example.g56172.screens.home

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.g56172.api.Days
import com.example.g56172.api.WeatherFiveDays
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    var _degreeText = MutableLiveData<String>()
    var _resumeText = MutableLiveData<String>()
    val resumeText: LiveData<String>
        get() = _resumeText
    var _dateText = MutableLiveData<String>()
    var _positionText = MutableLiveData<String>()
    var _numberMphText = MutableLiveData<String>()
    var _windDirectionText = MutableLiveData<String>()
    var _numberHumidityText = MutableLiveData<String>()
    val numberHumidityText: LiveData<String>
        get() = _numberHumidityText
    var _numberVisibilityText = MutableLiveData<String>()
    var _numberAirPressureText = MutableLiveData<String>()

    var weather = MutableLiveData<WeatherFiveDays>()

    companion object {
        fun dateTransform(date: String): String {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val dateTime: LocalDateTime = LocalDateTime.parse(date, formatter)
            return dateTime.dayOfWeek.name.substring(0, 2).lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + ". " + dateTime.dayOfMonth + " " + dateTime.month.name.lowercase()
                .substring(0, 3)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }

        fun toCelsius(temp: Double): String {
            return (temp - 273.15).toInt().toString()
        }

        fun meanDailyTemperature(days : List<Days>,dateText: String): String {
            var totalTempOfDay = 0.0
            var numberOfTempOfDay = 0
            for (i in 0..days.size){
                if(dateText == days.get(i).dt_txt.split(' ')[0] ){
                    totalTempOfDay+=days.get(i).main.temp
                    numberOfTempOfDay++
                } else {
                    return toCelsius(totalTempOfDay/numberOfTempOfDay)
                }
            }
            return toCelsius(totalTempOfDay/numberOfTempOfDay)
        }
    }

    fun changeDegree(degree: String) {
        _degreeText.value = degree
    }

    fun changeResume(resume: String) {
        _resumeText.value = resume
    }

    fun changeDate(dateText: String) {
        _dateText.value = dateText
    }

    fun changePosition(position: String) {
        _positionText.value = position
    }

    fun changeNumberMph(numberMph: String) {
        _numberMphText.value = numberMph
    }

    fun changeWindDirection(direction: String) {
        _windDirectionText.value = direction
    }

    fun changeNumberHumidity(numberHumidity: String) {
        _numberHumidityText.value = numberHumidity
    }

    fun changeNumberVisibility(numberVisibility: String) {
        _numberVisibilityText.value = numberVisibility
    }

    fun changeNumberAirPressure(numberAirPressure: String) {
        _numberAirPressureText.value = numberAirPressure
    }

    fun updateViewWithApiVar(myWeather: WeatherFiveDays) {
        val daysWeather = myWeather.list.get(0).weather.get(0)
        changeResume(daysWeather.main)
        changeDegree(meanDailyTemperature(myWeather.list,myWeather.list.get(0).dt_txt.split(' ')[0]))
        changeDate(dateTransform(myWeather.list.get(0).dt_txt))
        changePosition(myWeather.city.name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
        changeNumberMph(myWeather.list.get(0).wind.speed.toString())
        changeWindDirection(myWeather.list.get(0).wind.deg.toString() + "°")
        changeNumberHumidity(myWeather.list.get(0).main.humidity.toString())
        changeNumberVisibility(
            StringBuilder(
                myWeather.list.get(0).visibility.toString().substring(0, 2)
            ).insert(1, ',').toString()
        )
        changeNumberAirPressure(myWeather.list.get(0).main.pressure.toString())
    }


}