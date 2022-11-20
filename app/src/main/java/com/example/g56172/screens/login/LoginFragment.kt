package com.example.g56172.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.g56172.R
import com.example.g56172.database.UserLogin
import com.example.g56172.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(LoginViewModel()::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.initRepository(requireContext())
        viewModel.correctLogin.observe(viewLifecycleOwner, Observer<Boolean> { hasCorrectLogin ->
            if (!hasCorrectLogin) {
                val text = "Email is not valid. Please enter a valid email"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                toast.show()
            } else {
                viewModel.insertUserLogin()
                val text = "Email is valid"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                toast.show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                viewModel.hideKeyBoard(view, activity)
            }
        })

        val adapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            viewModel.setExistLogin()
        )
        binding.emailfields?.setAdapter(adapter)

        return binding.root
    }


}