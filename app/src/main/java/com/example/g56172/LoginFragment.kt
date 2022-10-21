package com.example.g56172

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.g56172.databinding.FragmentLoginBinding
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil.setContentView


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var bindingLand: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.loginButton.setOnClickListener {
            if (attemptLogin(binding)) {
                it.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                hideKeyBoard(it)
            }
        }
        bindingLand = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login_landscape,
            container,
            false
        )
        bindingLand.loginButton.setOnClickListener {
            if (attemptLogin(bindingLand)) {
                it.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                hideKeyBoard(it)
            }
        }
        return binding.root
    }

    private fun attemptLogin(binding: FragmentLoginBinding): Boolean {

        val email: String = binding.emailfield.text.toString()
        val password: String = binding.passwordfield.text.toString()

        if (!isEmailValid(email)) {
            val text = "Email is not valid. Please enter a valid email"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(activity, text, duration)
            toast.show()
            return false
        } else {
            println(email)
            println(password)
            return true
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun hideKeyBoard(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            .setContentView(R.layout.fragment_login_landscape)
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }
}