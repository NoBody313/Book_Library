package com.example.booklibrary.splashscreen.onboarding.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.booklibrary.R

class FirstScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Next button
        val nextButton : Button = view.findViewById(R.id.btn_next)
        nextButton.setOnClickListener{
            viewPager?.currentItem = 1

        }
        return view
    }
}