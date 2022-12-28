package com.example.g56172.api

data class CountryCode(
    val alpha2: String,
    val alpha3: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val numeric: Int
)