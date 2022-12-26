package com.example.booklibrary.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.addCallback
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booklibrary.databinding.FragmentHomeBinding
import com.example.booklibrary.tools.adapter.VerticalViewAdapter
import com.example.booklibrary.tools.data.DataBookItem
import com.example.booklibrary.ui.detail.BooksDetailActivity
import com.example.booklibrary.ui.search.SearchActivity
import com.google.gson.Gson
import java.io.IOException
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var verticalViewAdapter: VerticalViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        searchButton()
        onCallBack()
        getData()
    }

    private fun searchButton() {
        binding.btnSearch.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun setupRecyclerView() {
        verticalViewAdapter =
            VerticalViewAdapter(arrayListOf(), object : VerticalViewAdapter.OnAdapterListener {
                override fun onClick(result: DataBookItem.Result) {
                    startActivity(
                        Intent(context, BooksDetailActivity::class.java)
                            .putExtra("isbn", result.isbn)
                            .putExtra("authors", result.authors)
                            .putExtra("categories", result.categories)
                            .putExtra("longDescription", result.longDescription)
                        .putExtra("pageCount", result.pageCount)
//                        .putExtra("publishedDate", result.publishedDate)
                        .putExtra("status", result.status)
                        .putExtra("title", result.title)
                        .putExtra("thumbnailUrl", result.thumbnailUrl)

                )
            }

        })
        binding.rvDiscovery.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = verticalViewAdapter
        }
    }

    private fun getJsonData(fileName: String): List<DataBookItem.Result>? {
        val assetManager = resources.assets
        var result: List<DataBookItem.Result>? = null
        try {
            val inputStream = assetManager.open(fileName)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, Array<DataBookItem.Result>::class.java).toList()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun getData(){
        val testdata = getJsonData("books.json")
        if (testdata != null) {
            showResult(testdata)
        }
    }

    private fun showResult(results: List<DataBookItem.Result>?) {

        for (result in results!!)
            verticalViewAdapter.setData(results)
    }

    private fun onCallBack() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            fun handleOnBackPressed() {
                Log.d(TAG, "Fragment back pressed invoke")

                if (isEnabled) {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                    activity?.finishAffinity()
                }
            }
            handleOnBackPressed()
        }
        callback
    }
}