package com.example.g56172.screens.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.g56172.R
import com.example.g56172.databinding.FragmentAboutMeBinding

class AboutMeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAboutMeBinding>(
            inflater,
            R.layout.fragment_about_me,
            container,
            false
        )
        return binding.root
    }

}