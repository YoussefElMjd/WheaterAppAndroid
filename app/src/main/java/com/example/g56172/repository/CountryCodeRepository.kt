package com.example.g56172.repository

import android.content.Context
import com.example.g56172.database.*

class CountryCodeRepository(context : Context) {

    private var db: WeatherDataBase
    private var countryCodeDao: CountryCodeDao

    init {
        db = WeatherDataBase.getInstance(context)
        countryCodeDao = db.countryCodeDao
    }

    fun insert(country: String, alpha2 : String, alpha3 : String, numeric : Int, longitude: Double, latitude: Double) {
        val exist = countryCodeDao.get(country)
        if (exist == null) {
            countryCodeDao.insert(
                CountryCode(
                    country = country,
                    longitude = longitude,
                    latitude = latitude,
                    alpha2 = alpha2,
                    alpha3 = alpha3,
                    numeric = numeric
                )
            )
        }
    }

    fun getAll(): List<CountryCode> {
        return countryCodeDao.getAll()
    }

    fun getPosCountry(country: String): CountryCode {
        return countryCodeDao.get(country)
    }
}