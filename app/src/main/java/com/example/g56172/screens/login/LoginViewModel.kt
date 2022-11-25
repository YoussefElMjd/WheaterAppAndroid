package com.example.g56172.screens.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.repository.LoginRepository

class LoginViewModel() : ViewModel() {

    var _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    var _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    var _correctLogin = MutableLiveData<Boolean>()
    val correctLogin: LiveData<Boolean>
        get() = _correctLogin

    lateinit var repository: LoginRepository

    fun attemptLogin() {
        _correctLogin.value = isEmailValid() && _password.value.toString() != "null"
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
    }

    fun initRepository(context: Context) {
        repository = LoginRepository(context)
    }

    fun insertUserLogin() {
        _email.value?.let { repository.insert(it) }
    }

    fun getExistLogin() : ArrayList<String>{
        val logins = repository.getAll()
        val existLogins = arrayListOf<String>()
        if (logins != null) {
            for (login in logins){
                existLogins.add(login.login)
            }
        }
        return existLogins
    }


}
