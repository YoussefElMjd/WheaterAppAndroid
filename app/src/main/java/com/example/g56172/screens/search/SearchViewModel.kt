package com.example.g56172.screens.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var _searchField = MutableLiveData<String>()
    val searchField: LiveData<String>
        get() = _searchField


    fun onSearchField(){
        Log.i("Search",_searchField.value.toString())
    }
}