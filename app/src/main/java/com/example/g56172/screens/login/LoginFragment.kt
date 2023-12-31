package com.example.g56172.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.g56172.R
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
        setupViewModel()
        setupBinding()
        updateAdapter()

        return binding.root
    }

    private fun hideKeyBoard(view: View?, activity: FragmentActivity?) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun updateAdapter() {
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            viewModel.getExistLogin()
        )
        binding.emailfields.setAdapter(adapter)
        binding.emailfields.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                binding.emailfields.showDropDown()
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[LoginViewModel()::class.java]
        viewModel.initRepository(requireContext())
        viewModel.correctLogin.observe(viewLifecycleOwner, Observer { hasCorrectLogin ->
            if (!hasCorrectLogin) {
                val text = "Email is not valid. Please enter a valid email"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                hideKeyBoard(view, activity)
                toast.show()
            } else {
                viewModel.insertUserLogin()
                val text = "Email is valid"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                toast.show()
                updateAdapter()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                hideKeyBoard(view, activity)
            }
        })
    }

    private fun setupBinding() {
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}