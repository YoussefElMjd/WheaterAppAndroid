package com.example.g56172.database

import androidx.room.*

@Dao
interface CountryFavPosDao {
    @Insert
    fun insert(countryFavPos: CountryFavPos)


    @Delete
    fun delete(countryFavPos: CountryFavPos)


    @Update
    fun update(countryFavPos: CountryFavPos)


    @Query("DELETE FROM CountryFavPos")
    fun deleteAll()


    @Query("SELECT * FROM CountryFavPos")
    fun getAll() : List<CountryFavPos>

    @Query("SELECT * FROM CountryFavPos where country = :country")
    fun get(country: String) : CountryFavPos
}