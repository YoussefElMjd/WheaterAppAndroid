package com.example.g56172.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitApi {
    private const val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val jsonConverter: MoshiConverterFactory = MoshiConverterFactory.create()
    private val retrofitBuilder: Retrofit.Builder =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(jsonConverter)
    private val retrofit: Retrofit = retrofitBuilder.build()
    val myHttpClient = retrofit.create(MyRetroFitHttpClient::class.java)

}