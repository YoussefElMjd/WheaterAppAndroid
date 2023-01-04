package com.example.g56172.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyRetroFitHttpClient {


    @GET("forecast")
//    fun getWeather() : Call<WeatherFiveDays>
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") api_key: String
    ): Call<WeatherFiveDays>
}