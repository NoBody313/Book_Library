package com.example.booklibrary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booklibrary.databinding.FragmentHomeBinding
import com.example.booklibrary.tools.data.DataBook
import com.example.booklibrary.ui.home.adapter.VerticalViewAdapter
import com.google.gson.Gson
import java.io.IOException

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return (binding.root)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val testdata = getJsonData("books.json")

        binding.rvDiscovery.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = VerticalViewAdapter(testdata!!)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun getJsonData(fileName: String): DataBook? {
        val assetManager = resources.assets
        var result: DataBook? = null
        try {
            val inputStream = assetManager.open(fileName)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, DataBook::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}