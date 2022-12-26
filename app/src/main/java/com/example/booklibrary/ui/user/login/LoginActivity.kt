package com.example.booklibrary.ui.user.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.booklibrary.MainActivity
import com.example.booklibrary.R
import com.example.booklibrary.databinding.ActivityLoginBinding
import com.example.booklibrary.tools.user.SharedPreference
import com.example.booklibrary.ui.user.register.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    private lateinit var preference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        init()
        checkLogin()
        clickLogin()

        auth = FirebaseAuth.getInstance()

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Login Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.btnGoogle.setOnClickListener {
            loginGoogle()
        }
        super.onCreate(savedInstanceState)
    }

    private fun init() {
        preference = SharedPreference(this)
        binding.etEmail
        binding.etPassword
    }

    private fun checkLogin() {
        if (preference.isLogin()!!) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Login
    private fun clickLogin() {
        binding.btnLogin.setOnClickListener {

            // Default
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || email == "") {
                binding.etEmail.error = "Email is required"
                binding.etEmail.requestFocus()
            } else if (password.isEmpty() || password == "") {
                binding.etPassword.error = "Password is required"
                binding.etPassword.requestFocus()

            } else {
                preference.setLogin(true)
                preference.setEmail(email)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            loginFirebase(email, password)
        }
    }

    // Login Google
    private fun loginGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResult(task)
            }
        }

    private fun handleResult(task: Task<GoogleSignInAccount>?) {
        if (task!!.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUi(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUi(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginFirebase(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
        finishAffinity()
    }
}