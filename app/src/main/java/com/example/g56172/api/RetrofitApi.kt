package com.example.g56172.api

import androidx.lifecycle.LiveData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitApi {
    val baseUrl = "https://api.openweathermap.org/data/2.5/"
    val jsonConverter: MoshiConverterFactory = MoshiConverterFactory.create()
    val retrofitBuilder: Retrofit.Builder =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(jsonConverter)
    val retrofit = retrofitBuilder.build()
    val myHttpClient = retrofit.create(MyRetroFitHttpClient::class.java)

}