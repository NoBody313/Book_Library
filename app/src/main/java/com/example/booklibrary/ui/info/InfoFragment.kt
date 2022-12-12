package com.example.booklibrary.ui.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.booklibrary.databinding.FragmentInfoBinding
import com.example.booklibrary.ui.user.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InfoFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var _binding : FragmentInfoBinding? = null
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_info, container, false)
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return (binding.root)
//
//        authA()
//        logoutButton()
    }

//    private fun logoutButton() {
//        binding.btnLogout.setOnClickListener{
//            val intent = Intent(context, LoginActivity::class.java)
//            Firebase.auth.signOut()
//            startActivity(intent)
//        }
//    }
//
//    private fun authA(){
//        auth = FirebaseAuth.getInstance()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser

        binding.btnLogout.setOnClickListener {
            logoutButton()
        }
    }

    private fun logoutButton() {
        auth = FirebaseAuth.getInstance()
        Firebase.auth.signOut()
        val i = Intent(context, LoginActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}