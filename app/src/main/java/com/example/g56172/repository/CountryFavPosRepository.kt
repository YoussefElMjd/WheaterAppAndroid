package com.example.g56172.repository

import android.content.Context
import com.example.g56172.database.CountryFavPos
import com.example.g56172.database.CountryFavPosDao
import com.example.g56172.database.WeatherDataBase

class CountryFavPosRepository(context: Context) {
    private lateinit var db: WeatherDataBase
    private lateinit var countryFavPosDao: CountryFavPosDao

    init {
        db = WeatherDataBase.getInstance(context)
        countryFavPosDao = db.countryFavPosDao
    }

    fun insert(country: String, longitude: Double, latitude: Double) {
        val exist = countryFavPosDao.get(country)
        if (exist == null) {
            countryFavPosDao.insert(
                CountryFavPos(
                    country = country,
                    longitude = longitude,
                    latitude = latitude
                )
            )
        }
    }

    fun getAll(): List<CountryFavPos> {
        return countryFavPosDao.getAll()
    }
}