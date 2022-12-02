package com.example.g56172.screens.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.repository.LoginRepository

class LoginViewModel : ViewModel() {

    var _email = MutableLiveData<String>()

    var _password = MutableLiveData<String>()

    var _correctLogin = MutableLiveData<Boolean>()


    private lateinit var repository: LoginRepository

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

    fun getExistLogin(): List<String> {
        return repository.getEmail()
    }


}
