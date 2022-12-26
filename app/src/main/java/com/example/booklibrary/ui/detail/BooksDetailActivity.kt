package com.example.booklibrary.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.booklibrary.R
import com.example.booklibrary.databinding.ActivityBooksDetailBinding

class BooksDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBooksDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_detail)
        binding = ActivityBooksDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

//        val dataBook = intent.getParcelableExtra<DataBookItem>(DATA_BOOK) as DataBook

        //Call JSON Data
//        val dataBookItem = applicationContext.assets
//            .open("books.json").bufferedReader().use {
//                it.readText()
//            }
//
//        val dataBook = Gson().fromJson(dataBookItem, DataBookItem::class.java)
//
//        Glide.with(this).load(dataBook.thumbnailUrl).into(binding.imgDetailBook)
//        binding.apply {
//            tvDetailTitleBook.text = findViewById(R.id.tv_detail_title_book)
//            tvNumberIsbn.text = findViewById(R.id.tv_number_isbn)
//            tvNumberPage.text = findViewById(R.id.tv_number_page)
//            tvStatusInfo.text = findViewById(R.id.tv_status_info)
//            tvBookPublishDate.text = findViewById(R.id.tv_book_publish_date)
//            tvBookAuthor.text = findViewById(R.id.tv_book_author)
//            tvBookCategory.text = findViewById(R.id.tv_book_category)
//            tvDetailAboutBook.text = findViewById(R.id.tv_detail_about_book)
//        }

        val intentTitle = intent.getStringExtra("title")
        binding.apply {
            tvDetailTitleBook.text = intentTitle
        }

        val intentIsbn = intent.getStringExtra("isbn")
        binding.apply {
            tvNumberIsbn.text = intentIsbn
        }

        val intentPage = intent.getStringExtra("pageCount")
        binding.apply {
            tvNumberPage.text = intentPage
        }

        val intentStatus = intent.getStringExtra("status")
        binding.apply {
            tvStatusInfo.text = intentStatus
        }

        val intentAuthor = intent.getStringArrayListExtra("authors")
        binding.apply {
            tvBookAuthor.text = intentAuthor?.toList().toString()
        }

        val intentCategory = intent.getStringArrayListExtra("categories")
        binding.apply {
            tvBookCategory.text = intentCategory?.toList().toString()
        }

        val intentDesc = intent.getStringExtra("longDescription")
        binding.apply {
            tvDetailAboutBook.text = intentDesc
        }

        val intentImage = intent.getStringExtra("thumbnailUrl")
        binding.apply {
            Glide.with(imgDetailBook.context).load(intentImage).into(imgDetailBook)
        }
    }

    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
        finish()
    }
}