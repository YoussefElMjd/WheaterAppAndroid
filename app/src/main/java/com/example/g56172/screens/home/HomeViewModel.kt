package com.example.g56172.screens.home

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.R


class HomeViewModel : ViewModel()  {

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

    var _imageView = MutableLiveData<Int>()
    val imageView: LiveData<Int>
        get() = _imageView



    fun changeDegree(degree : String){
        _degreeText.value = degree
    }

    fun changeResume(resume : String){
        _resumeText.value = resume
    }

    fun changeDate(dateText : String){
        _dateText.value = dateText
    }

    fun changePosition(position : String){
        _positionText.value = position
    }
    fun changeNumberMph(numberMph : String){
        _numberMphText.value = numberMph
    }

    fun changeNumberHumidity(numberHumidity : String){
        _numberHumidityText.value = numberHumidity
    }

    fun changeProgressBar(progressBar: ProgressBar, progress: Int){
        progressBar.setProgress(progress)
    }

    fun changeNumberVisibility(numberVisibility : String){
        _numberVisibilityText.value = numberVisibility
    }

    fun changeNumberAirPressure(numberAirPressure : String){
        _numberAirPressureText.value = numberAirPressure
    }

    fun changeImageView(view: ImageView){
        when(_resumeText.value.toString()){
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

    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: String?) {
        if (imageUri == null) {
            view.setImageURI(null)
        } else {
            view.setImageURI(Uri.parse(imageUri))
        }
    }

    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: Uri?) {
        view.setImageURI(imageUri)
    }

    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }

    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

}