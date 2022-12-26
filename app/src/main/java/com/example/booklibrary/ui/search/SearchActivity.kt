package com.example.booklibrary.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booklibrary.MainActivity
import com.example.booklibrary.databinding.ActivitySearchBinding
import com.example.booklibrary.tools.adapter.VerticalViewAdapter
import com.example.booklibrary.tools.data.DataBookItem
import com.example.booklibrary.ui.detail.BooksDetailActivity
import com.google.gson.Gson
import java.io.IOException

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var verticalViewAdapter: VerticalViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupRecyclerView()
        getData()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.svSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                val testdata = getJsonData("books.json")
                testdata?.filter {
                    it.title.contains(query.toString())
                }
                if (testdata != null) {
                    showResult(testdata)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val testdata = getJsonData("books.json")
                    val result = testdata?.filter {
                        it.title.contains(newText, true)
                    }
                    if (result != null) {
                        verticalViewAdapter.setData(result)
                    }
                }


                return true
            }

        })
    }

    // Search View


    // RecyclerView
    private fun setupRecyclerView() {
        verticalViewAdapter =
            VerticalViewAdapter(arrayListOf(), object : VerticalViewAdapter.OnAdapterListener {
                override fun onClick(result: DataBookItem.Result) {
                    startActivity(
                        Intent(this@SearchActivity, BooksDetailActivity::class.java)
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
        binding.rvSearch.apply {
            layoutManager =
                GridLayoutManager(this@SearchActivity, 3, GridLayoutManager.VERTICAL, false)
            adapter = verticalViewAdapter
        }
    }

    // Get Data
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

    private fun getData() {
        val testdata = getJsonData("books.json")
        if (testdata != null) {
            showResult(testdata)
        }
    }

    private fun showResult(results: List<DataBookItem.Result>?) {
        for (result in results!!)
            verticalViewAdapter.setData(results)
    }

    // Back Press
    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}