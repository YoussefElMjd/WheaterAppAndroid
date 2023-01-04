package com.example.g56172.database

import androidx.room.*

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
    fun getAll(): List<CountryCode>

    @Query("SELECT * FROM CountryCode where country = :country")
    fun get(country: String): CountryCode
}