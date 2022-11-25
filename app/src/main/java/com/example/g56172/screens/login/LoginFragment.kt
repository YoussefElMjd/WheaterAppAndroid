package com.example.g56172.screens.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
                updateAdapter()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                hideKeyBoard(view, activity)
            }
        })

        updateAdapter()

        return binding.root
    }

    fun hideKeyBoard(view: View?, activity: FragmentActivity?) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun updateAdapter() {
        val adapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            viewModel.getExistLogin()
        )
        binding.emailfields.setAdapter(adapter)
        binding.emailfields.setOnFocusChangeListener { _, _ ->
            binding.emailfields.showDropDown()
        }

    }

}