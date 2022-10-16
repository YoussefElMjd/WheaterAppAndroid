package com.example.g56172

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.g56172.data.UserData
import com.example.g56172.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userData : UserData = UserData("Youssef@gmail.com","test")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.loginButton.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin(){

        var email: String = binding.emailfield.text.toString()
        var password: String = binding.passwordfield.text.toString()

        if(!isEmailValid(email)){
            val text = "Email is not valid. Please enter a valid email"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(this,text,duration)
            toast.show()
        } else{
            println(email)
            println(password)
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}