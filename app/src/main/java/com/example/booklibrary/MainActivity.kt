package com.example.booklibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.booklibrary.databinding.ActivityMainBinding
import com.example.booklibrary.tools.session.LoginPref
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var session: LoginPref
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Session
        session = LoginPref(this)
        session.checkLogin()

        val navView: BottomNavigationView = binding.navMenu

        val navController = findNavController(R.id.nav_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_menu,
                R.id.info_menu
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}