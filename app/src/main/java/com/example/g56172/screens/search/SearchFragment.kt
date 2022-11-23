package com.example.g56172.screens.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.g56172.R
import com.example.g56172.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        var country = listOf("Belgique","France","Japon","Epsagne")
        var adaptater = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,country)
        binding.searchField.setAdapter(adaptater)
        binding.searchField.setOnFocusChangeListener{_, _ ->
            binding.searchField.showDropDown()
        }

        val myListAdapter = activity?.let { CustomListAdapter(it,country) }
        binding.favPosListView.setAdapter(myListAdapter)

        binding.favPosListView.setOnItemClickListener(){adapterView, view, position, id ->
            Log.i("Search", adapterView.getItemAtPosition(position).toString())
            Log.i("Search", adapterView.getItemIdAtPosition(position).toString())

        }
        return binding.root
    }
}