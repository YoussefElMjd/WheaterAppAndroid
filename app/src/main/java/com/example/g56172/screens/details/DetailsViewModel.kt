package com.example.g56172.screens.details

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.R

class DetailsViewModel : ViewModel() {

    var _days1Text = MutableLiveData<String>()
    val days1Text: LiveData<String>
        get() = _days1Text

    var _days1Max = MutableLiveData<String>()
    val days1MaxText: LiveData<String>
        get() = _days1Max

    var _days1Min = MutableLiveData<String>()
    val days1Min: LiveData<String>
        get() = _days1Min


    var _days2Text = MutableLiveData<String>()
    val days2Text: LiveData<String>
        get() = _days2Text

    var _days2Max = MutableLiveData<String>()
    val days2MaxText: LiveData<String>
        get() = _days2Max

    var _days2Min = MutableLiveData<String>()
    val days2Min: LiveData<String>
        get() = _days2Min


    var _days3Text = MutableLiveData<String>()
    val days3Text: LiveData<String>
        get() = _days3Text

    var _days3Max = MutableLiveData<String>()
    val days3MaxText: LiveData<String>
        get() = _days3Max

    var _days3Min = MutableLiveData<String>()
    val days3Min: LiveData<String>
        get() = _days3Min


    var _days4Text = MutableLiveData<String>()
    val days4Text: LiveData<String>
        get() = _days4Text

    var _days4Max = MutableLiveData<String>()
    val days4MaxText: LiveData<String>
        get() = _days4Max

    var _days4Min = MutableLiveData<String>()
    val days4Min: LiveData<String>
        get() = _days4Min

    var _days5Text = MutableLiveData<String>()
    val days5Text: LiveData<String>
        get() = _days5Text

    var _days5Max = MutableLiveData<String>()
    val days5MaxText: LiveData<String>
        get() = _days5Max

    var _days5Min = MutableLiveData<String>()
    val days5Min: LiveData<String>
        get() = _days5Min

    fun changeDays1Text(days1Text : String, days1Max : String, days1Min : String){
        _days1Text.value = days1Text
        _days1Max.value = days1Max
        _days1Min.value = days1Min
    }
    fun changeDays2Text(days2Text : String, days2Max : String, days2Min : String){
        _days2Text.value = days2Text
        _days2Max.value = days2Max
        _days2Min.value = days2Min
    }

    fun changeDays3Text(days3Text : String, days3Max : String, days3Min : String){
        _days3Text.value = days3Text
        _days3Max.value = days3Max
        _days3Min.value = days3Min
    }

    fun changeDays4Text(days4Text : String, days4Max : String, days4Min : String){
        _days4Text.value = days4Text
        _days4Max.value = days4Max
        _days4Min.value = days4Min
    }

    fun changeDays5Text(days5Text : String, days5Max : String, days5Min : String){
        _days5Text.value = days5Text
        _days5Max.value = days5Max
        _days5Min.value = days5Min
    }
    
    fun changeImageView(view: ImageView){
        when("Clear"){
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