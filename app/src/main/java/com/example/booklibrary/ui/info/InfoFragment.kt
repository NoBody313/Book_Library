package com.example.booklibrary.ui.info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booklibrary.R
import com.example.booklibrary.databinding.FragmentInfoBinding
import com.example.booklibrary.ui.user.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InfoFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_info, container, false)
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return (binding.root)

        authA()
        logoutButton()
    }

    private fun logoutButton() {
        binding.btnLogout.setOnClickListener{
            val intent = Intent(context, LoginActivity::class.java)
            Firebase.auth.signOut()
            startActivity(intent)
        }
    }

    private fun authA(){
        auth = FirebaseAuth.getInstance()
    }
}