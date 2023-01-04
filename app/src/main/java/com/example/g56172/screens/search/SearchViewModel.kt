package com.example.g56172.screens.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.R
import com.example.g56172.api.CountryCodes
import com.example.g56172.repository.CountryCodeRepository
import com.example.g56172.repository.CountryFavPosRepository
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class SearchViewModel : ViewModel() {
    var searchField = MutableLiveData<String>()


    lateinit var countryCodeRepository: CountryCodeRepository
    lateinit var countryFavRepository: CountryFavPosRepository

    fun getSearchFav(): ArrayList<String> {
        val countrys = countryFavRepository.getAll()
        val existCountry = arrayListOf<String>()
        if (countrys != null) {
            for (country in countrys) {
                existCountry.add(country.country)
            }
        }
        return existCountry
    }

    fun getSearchCountry(): ArrayList<String> {
        val countrys = countryCodeRepository.getAll()
        val existCountry = arrayListOf<String>()
        if (countrys != null) {
            for (country in countrys) {
                existCountry.add(country.country)
            }
        }
        return existCountry
    }

    fun initRepository(context: Context) {
        countryFavRepository = CountryFavPosRepository(context)
        countryCodeRepository = CountryCodeRepository(context)
        val inputStream = context.resources.openRawResource(R.raw.country_code)
        val reader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(reader)
        val stringBuilder = StringBuilder()
        var line: String? = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }
        val jsonCountry = stringBuilder.toString()
        bufferedReader.close()
        reader.close()
        inputStream.close()
        val gson = Gson()
        val countryCodes = gson.fromJson<CountryCodes>(jsonCountry, CountryCodes::class.java)
        for (i in 0..countryCodes.country_codes.size - 1) {
            var country = countryCodes.country_codes.get(i)
            countryCodeRepository.insert(
                country.country,
                country.alpha2,
                country.alpha3,
                country.numeric,
                country.longitude,
                country.latitude
            )
        }
    }


}