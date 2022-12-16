package com.example.g56172.api

data class WeatherFiveDays(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Days>,
    val message: Int
)