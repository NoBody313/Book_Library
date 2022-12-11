package com.example.booklibrary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booklibrary.databinding.ActivityTesterBinding
import com.example.booklibrary.tools.data.DataBookItem
import com.example.booklibrary.ui.detail.BooksDetailActivity
import com.example.booklibrary.ui.home.adapter.VerticalViewAdapter
import com.google.gson.Gson
import java.io.IOException

class TesterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTesterBinding
    private lateinit var  verticalViewAdapter: VerticalViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTesterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupRecyclerView()
        getData()
    }

//    private fun setupRecyclerView(){
//
//        verticalViewAdapter = VerticalViewAdapter(arrayListOf(), object : VerticalViewAdapter.OnAdapterListener{
//            override fun onClick(result: DataBookItem.Result) {
//                startActivity(
//                    Intent(this@TesterActivity, BooksDetailActivity::class.java)
//                        .putExtra("isbn", result.isbn)
//                )
//            }
//
//        })
//        binding.rvDiscovery.apply {
//            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
//            adapter = verticalViewAdapter
//        }
//    }

    private fun getJsonData(fileName: String): List<DataBookItem.Result>?  {
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

    private fun showResult(results: List<DataBookItem.Result>?){

        for (result in results!!)
            verticalViewAdapter.setData(results)
    }
}