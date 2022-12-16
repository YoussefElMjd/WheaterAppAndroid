package com.example.g56172.screens.home

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.g56172.api.WeatherFiveDays
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    var _degreeText = MutableLiveData<String>()
    val degreeText: LiveData<String>
        get() = _degreeText

    var _resumeText = MutableLiveData<String>()
    val resumeText: LiveData<String>
        get() = _resumeText

    var _dateText = MutableLiveData<String>()
    val dateText: LiveData<String>
        get() = _dateText

    var _positionText = MutableLiveData<String>()
    val positionText: LiveData<String>
        get() = _positionText

    var _numberMphText = MutableLiveData<String>()
    val numberMphText: LiveData<String>
        get() = _numberMphText

    var _windDirectionText = MutableLiveData<String>()
    val windDirectionText: LiveData<String>
        get() = _windDirectionText

    var _numberHumidityText = MutableLiveData<String>()
    val numberHumidityText: LiveData<String>
        get() = _numberHumidityText

    var _progressBar = MutableLiveData<ProgressBar>()
    val progressBar: LiveData<ProgressBar>
        get() = _progressBar

    var _numberVisibilityText = MutableLiveData<String>()
    val numberVisibilityText: LiveData<String>
        get() = _numberVisibilityText

    var _numberAirPressureText = MutableLiveData<String>()
    val numberAirPressureText: LiveData<String>
        get() = _numberAirPressureText

    var _srcDrawableImage = MutableLiveData<Drawable?>()
    val srcDrawableImage: LiveData<Drawable?>
        get() = _srcDrawableImage


    var weather = MutableLiveData<WeatherFiveDays>()

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

    fun changeNumberHumidity(numberHumidity: String) {
        _numberHumidityText.value = numberHumidity
    }

    fun changeNumberVisibility(numberVisibility: String) {
        _numberVisibilityText.value = numberVisibility
    }

    fun changeNumberAirPressure(numberAirPressure: String) {
        _numberAirPressureText.value = numberAirPressure
    }

    fun updateViewWithApiVar(myWeather : WeatherFiveDays){
        val daysWeather = myWeather.list.get(0).weather.get(0)
        Log.i("WeatherApi", daysWeather.description)
        _resumeText.value = daysWeather.description
        val celsius = myWeather.list.get(0).main.temp - 273.15
        _degreeText.value = celsius.toInt().toString()
        _resumeText.value = daysWeather.main
        val date = myWeather.list.get(0).dt_txt
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime: LocalDateTime = LocalDateTime.parse(date, formatter)
        val dateToInsert = dateTime.dayOfWeek.name.substring(0, 2).lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + ". " + dateTime.dayOfWeek.value + " " + dateTime.month.name.lowercase()
            .substring(0, 3)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        _dateText.value = dateToInsert
        _positionText.value = myWeather.city.name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        _numberMphText.value = myWeather.list.get(0).wind.speed.toString()
        _windDirectionText.value = myWeather.list.get(0).wind.deg.toString() + "°"
        _numberHumidityText.value = myWeather.list.get(0).main.humidity.toString()
        _numberVisibilityText.value = StringBuilder(
            myWeather.list.get(0).visibility.toString().substring(0, 2)
        ).insert(1, ',').toString()
        _numberAirPressureText.value =
            myWeather.list.get(0).main.pressure.toString()
    }

}