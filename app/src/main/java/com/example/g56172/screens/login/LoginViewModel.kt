package com.example.g56172.screens.login

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g56172.repository.LoginRepository

class LoginViewModel : ViewModel() {

    var email = MutableLiveData<String>()

    var password = MutableLiveData<String>()

    var correctLogin = MutableLiveData<Boolean>()


    private lateinit var repository: LoginRepository

    fun attemptLogin() {
        correctLogin.value = isEmailValid() && password.value.toString() != "null"
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun initRepository(context: Context) {
        repository = LoginRepository(context)
    }

    fun insertUserLogin() {
        email.value?.let { repository.insert(it) }
    }

    fun getExistLogin(): List<String> {
        return repository.getEmail()
    }


}
