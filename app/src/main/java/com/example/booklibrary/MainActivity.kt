package com.example.booklibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibrary.tools.session.LoginPref

class MainActivity : AppCompatActivity() {

    private lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Session
        session = LoginPref(this)
        session.checkLogin()

    }
}