package com.example.g56172.screens.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        viewModel = ViewModelProvider(this).get(SearchViewModel()::class.java)
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.initRepository(requireContext())
        binding.searchField.setOnFocusChangeListener { _, _ ->
            binding.searchField.showDropDown()
        }
//        binding.searchField.setOnItemClickListener(adapterView)
        viewModel._searchField.observe(viewLifecycleOwner) { search ->
            viewModel.onSearchField()
        }

        binding.favPosListView.setOnItemClickListener { adapterView, view, position, id ->
//            Log.i("Search", adapterView.getItemIdAtPosition(position).toString())
            var countryChose = viewModel.repository.getPosCountry(adapterView.getItemAtPosition(position).toString())
            HomeFragment.makeCallApi(countryChose.latitude,countryChose.longitude)
            findNavController().navigate(R.id.action_searchFragment_to_homesFragment)
        }
        updateAdapter()
        return binding.root
    }

    private fun updateAdapter() {

        val myListAdapter = activity?.let { CustomListAdapter(it, viewModel.getSearchFav()) }
        binding.favPosListView.setAdapter(myListAdapter)
        var adaptater =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, viewModel.getSearchFav())
        binding.searchField.setAdapter(adaptater)
    }
}