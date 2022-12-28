package com.example.g56172.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserLogin::class,CountryFavPos::class,CountryCode::class], version = 3, exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {

    abstract val userLoginDao: UserLoginDao
    abstract val countryFavPosDao: CountryFavPosDao
    abstract val countryCodeDao: CountryCodeDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDataBase::class.java,
                        "weather_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}