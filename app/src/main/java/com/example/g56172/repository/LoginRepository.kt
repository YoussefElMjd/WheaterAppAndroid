package com.example.g56172.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.g56172.database.UserLogin
import com.example.g56172.database.UserLoginDao
import com.example.g56172.database.WeatherDataBase

class LoginRepository(context: Context) {
    private lateinit var db: WeatherDataBase
    private lateinit var loginDao: UserLoginDao

    init{
        db = WeatherDataBase.getInstance(context)
        loginDao = db.userLoginDao
    }

    fun insert(email:String){
        val exist = loginDao.get(email);
        if(exist == null){
            loginDao.insert(UserLogin(login=email))
        }
    }

    fun getAll() : List<UserLogin>? {
        return loginDao.getAll()
    }

    fun getEmail() : List<String> {
        return loginDao.getEmail()
    }
}