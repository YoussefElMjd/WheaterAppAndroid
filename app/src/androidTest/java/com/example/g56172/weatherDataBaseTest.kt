package com.example.g56172

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.g56172.database.UserLogin
import com.example.g56172.database.UserLoginDao
import com.example.g56172.database.WeatherDataBase
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDataBaseTest {
    private lateinit var userLoginDao: UserLoginDao
    private lateinit var db: WeatherDataBase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WeatherDataBase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        userLoginDao = db.userLoginDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val userLogin = UserLogin(login = "Youssef.ElMajdoul@gmail.com")
        userLoginDao.insert(userLogin)
        val lastUser = userLoginDao.get("Youssef.ElMajdoul@gmail.com")
        assertEquals(lastUser?.login, "Youssef.ElMajdoul@gmail.com")
    }
}