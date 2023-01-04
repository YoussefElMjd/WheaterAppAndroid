package com.example.g56172.repository

import android.content.Context
import com.example.g56172.database.UserLogin
import com.example.g56172.database.UserLoginDao
import com.example.g56172.database.WeatherDataBase
import java.time.LocalDateTime

class LoginRepository(context: Context) {
    private var db: WeatherDataBase
    private var loginDao: UserLoginDao

    init {
        db = WeatherDataBase.getInstance(context)
        loginDao = db.userLoginDao
    }

    fun insert(email: String) {
        val exist = loginDao.get(email)
        if (exist == null) {
            loginDao.insert(UserLogin(login = email))
        } else {
            updateDateConnection(email)
        }
    }

    fun getAll(): List<UserLogin>? {
        return loginDao.getAll()
    }

    fun getEmail(): List<String> {
        return loginDao.getEmail()
    }

    private fun updateDateConnection(email: String) {
        loginDao.updateDateConnection(email, LocalDateTime.now().toString())
    }
}