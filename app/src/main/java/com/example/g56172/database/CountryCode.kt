package com.example.g56172.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryCode")
data class CountryCode(
    @PrimaryKey(autoGenerate = true)
    var countryCodeId: Long = 0L,
    @ColumnInfo(name = "country")
    var country : String = "",
    @ColumnInfo(name = "alpha2")
    var alpha2 : String = "",
    @ColumnInfo(name = "alpha3")
    var alpha3 : String = "",
    @ColumnInfo(name = "numeric")
    var numeric : Int,
    @ColumnInfo(name = "longitude")
    var longitude : Double,
    @ColumnInfo(name = "latitude")
    var latitude : Double
)
