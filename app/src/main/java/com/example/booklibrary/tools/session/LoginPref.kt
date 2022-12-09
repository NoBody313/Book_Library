package com.example.booklibrary.tools.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.booklibrary.ui.user.login.LoginActivity

class LoginPref(context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private var context: Context
    var PRIVATE_MODE : Int = 0

    init {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object{
        val PREF_NAME = "Login Preference"
        val IS_LOGIN = "Is Logged In"
        val KEY_EMAIL = "email"
        val KEY_PASSWORD = "password"

    }

    fun createLoginSession(email: String, password: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PASSWORD, password)
        editor.commit()
    }

    fun checkLogin() {
        if (!this.isLoggedIn()){
            var i : Intent = Intent(context, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }

    fun getUserDetail(): Map<String, String> {
        var user: Map<String, String> = HashMap<String, String>()
        (user as HashMap).put(KEY_EMAIL, pref.getString(KEY_EMAIL, null)!!)
        (user as HashMap).put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null)!!)
        return user
    }

    fun LogoutUser() {
        editor.commit()
        editor.clear()
        var i : Intent = Intent(context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }
}