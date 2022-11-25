package com.example.g56172.screens.home

import android.app.Application
import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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


}