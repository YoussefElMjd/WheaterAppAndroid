package com.example.g56172.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface CountryCodeDao {
    @Insert
    fun insert(countryCode: CountryCode)


    @Delete
    fun delete(countryCode: CountryCode)


    @Update
    fun update(countryCode: CountryCode)


    @Query("DELETE FROM CountryCode")
    fun deleteAll()


    @Query("SELECT * FROM CountryCode")
    fun getAll() : List<CountryCode>

    @Query("SELECT * FROM CountryCode where country = :country")
    fun get(country: String) : CountryCode
}