package com.example.booklibrary.splashscreen.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.booklibrary.R
import com.example.booklibrary.splashscreen.onboarding.screen.FirstScreen
import com.example.booklibrary.splashscreen.onboarding.screen.SecondScreen
import com.example.booklibrary.splashscreen.onboarding.screen.ThirdScreen

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPagerAdapter : ViewPager2 = view.findViewById(R.id.viewPager)
        viewPagerAdapter.adapter = adapter

        return view
    }

}