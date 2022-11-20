package com.example.g56172.screens.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.lifecycle.viewModelScope
import com.example.g56172.MainActivity
import com.example.g56172.database.UserLogin
import com.example.g56172.database.WeatherDataBase
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

    fun hideKeyBoard(view: View?, activity: FragmentActivity?) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun initRepository(context: Context) {
        repository = LoginRepository(context)
    }

    fun insertUserLogin() {
        _email.value?.let { repository.insert(it) }
    }

    fun setExistLogin() : ArrayList<String>{
        var logins = repository.getAll()
        var existLogins = arrayListOf<String>()
        for (login in logins!!){
            Log.i("Login",login.login)
            existLogins.add(login.login)
        }
        return existLogins
    }


}
