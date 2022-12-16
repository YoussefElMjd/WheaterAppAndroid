package com.example.g56172.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyRetroFitHttpClient {

    @GET("forecast?lat=50.8333&lon=4.35&appid=c9c5ab7f1eeab26b293dabfb986d199f")
    fun getWeather() : Call<WeatherFiveDays>
//    fun getWeather(@Query("lat") lat : Double, @Query("lon") lon : Double) : Call<Days>
}