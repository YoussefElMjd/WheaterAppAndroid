package com.example.g56172.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.g56172.R
import com.example.g56172.databinding.FragmentSearchBinding
import com.example.g56172.screens.home.HomeFragment

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )
        setupViewModel()
        setupBinding()
        updateAdapter()
        return binding.root
    }

    private fun updateAdapter() {
        val myListAdapter = activity?.let { CustomListAdapter(it, viewModel.getSearchFav()) }
        binding.favPosListView.setAdapter(myListAdapter)
        val adaptater =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                viewModel.getSearchCountry()
            )
        binding.searchField.setAdapter(adaptater)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(SearchViewModel()::class.java)
        viewModel.initRepository(requireContext())
    }

    private fun setupBinding() {
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchField.setOnFocusChangeListener { _, _ ->
            binding.searchField.showDropDown()
        }
        binding.searchButton.setOnClickListener {
            val country = viewModel.searchField.value?.let { it1 ->
                viewModel.countryCodeRepository.getPosCountry(
                    it1
                )
            }
            if (country == null) {
                Toast.makeText(
                    requireContext(),
                    "No countries found, please try again",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                viewModel.countryFavRepository.insert(
                    country.country,
                    country.longitude,
                    country.latitude
                )
                HomeFragment.makeCallApi(country.latitude, country.longitude)
                findNavController().navigate(R.id.action_searchFragment_to_homesFragment)
            }

        }
        binding.favPosListView.setOnItemClickListener { adapterView, view, position, id ->
            val countryChose = viewModel.countryFavRepository.getPosCountry(
                adapterView.getItemAtPosition(position).toString()
            )
            HomeFragment.makeCallApi(countryChose.latitude, countryChose.longitude)
            findNavController().navigate(R.id.action_searchFragment_to_homesFragment)
        }
    }

}