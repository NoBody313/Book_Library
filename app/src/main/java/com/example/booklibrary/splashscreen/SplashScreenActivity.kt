package com.example.booklibrary.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibrary.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

    }
}