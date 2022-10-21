package com.example.g56172

import android.content.Context
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


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
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
            val text = "Email is valid"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(activity, text, duration)
            toast.show()
            return true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun hideKeyBoard(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}