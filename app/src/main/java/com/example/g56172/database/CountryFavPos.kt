package com.example.g56172.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryFavPos")
data class CountryFavPos(
    @PrimaryKey(autoGenerate = true)
    var countryId: Long = 0L,
    @ColumnInfo(name = "country")
    var country: String = "",
    @ColumnInfo(name = "longitude")
    var longitude: Double,
    @ColumnInfo(name = "latitude")
    var latitude: Double
)
