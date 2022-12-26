package com.example.booklibrary.ui.info

import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.booklibrary.databinding.FragmentInfoBinding
import com.example.booklibrary.tools.user.SharedPreference
import com.example.booklibrary.ui.user.login.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InfoFragment : Fragment() {

//    private var _binding: FragmentInfoBinding? = null
    private lateinit var binding: FragmentInfoBinding
    private lateinit var preference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(layoutInflater)

        // Session
        checkLogin()
        onCallBack()
        clickLogout()
        return (binding.root)
    }

    private fun checkLogin() {
        preference = SharedPreference(activity)
        if (preference.isLogin() == false) {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun clickLogout() {
        binding.btnLogout.setOnClickListener {
            preference.removeData()
            val intent = Intent(activity, LoginActivity::class.java)
            Firebase.auth.signOut()
            startActivity(intent)
            activity?.finish()
        }

    }

    private fun onCallBack(): OnBackPressedCallback {
        val callback =
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoke")

                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
                handleOnBackPressed()
            }
        return callback
    }
}