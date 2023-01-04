package com.example.g56172.screens.search

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.g56172.R

class CustomListAdapter(private val context: Activity, private val countryName: List<String>) :
    ArrayAdapter<String>(context, R.layout.list_item_layout, countryName) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item_layout, null, true)

        val countryText = rowView.findViewById(R.id.countryNameText) as TextView
        val icon = rowView.findViewById(R.id.searchIcon) as TextView

        countryText.text = countryName[position]
        return rowView
    }
}