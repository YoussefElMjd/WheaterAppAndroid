package com.example.g56172.screens.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.repository.CountryFavPosRepository
import com.example.g56172.repository.LoginRepository

class SearchViewModel : ViewModel() {
    var _searchField = MutableLiveData<String>()
    val searchField: LiveData<String>
        get() = _searchField

    lateinit var repository: CountryFavPosRepository

    fun onSearchField(){
        Log.i("Search",_searchField.value.toString())
    }

    fun getSearchFav() : ArrayList<String>{
       val countrys = repository.getAll()
        val existCountry = arrayListOf<String>()
        if (countrys != null) {
            for (country in countrys){
                existCountry.add(country.country)
            }
        }
        return existCountry
    }

    fun initRepository(context: Context) {
        repository = CountryFavPosRepository(context)
        repository.insert("Belgique",4.475402500000001,50.499527)
        repository.insert("France",2.209666999999996,46.232192999999995)
        repository.insert("Allemagne ",10.452764000000002,51.1657065)
        repository.insert("Royaume-Uni ",-3.432277499999998,54.633221000000006)
    }



}