package com.example.booklibrary.tools.user

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context?) {

    // Shared Pref Mode
    private val PRIVATE_MODE: Int = 0

    // Shared Pref
    private val PREF_NAME = "SharedPreference"
    private val IS_LOGIN = "Is login"

    private val preference: SharedPreferences? =
        context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    private val editor: SharedPreferences.Editor? = preference?.edit()

    fun setLogin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setEmail(email: String?) {
        editor?.putString("email", email)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return preference?.getBoolean(IS_LOGIN, false)
    }

    fun getEmail(): String? {
        return preference?.getString("email", "")
    }

    fun removeData() {
        editor?.clear()
        editor?.commit()
    }

}

//    private var editor: SharedPreferences.Editor
//    private var context: Context

//    init {
//        this.context = context
//        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
//        editor = preferences.edit()
//    }
//
//    companion object {
//        const val PREF_NAME = "Login Preference"
//        const val IS_LOGIN = "Is Logged In"
//        const val KEY_EMAIL = "email"
//        const val KEY_PASSWORD = "password"
//
//    }
//
//    fun createLoginSession(email: String, password: String) {
//        editor.putBoolean(IS_LOGIN, true)
//        editor.putString(KEY_EMAIL, email)
//        editor.putString(KEY_PASSWORD, password)
//        editor.commit()
//    }
//
//    fun checkLogin() {
//        if (!isLoginUser()) {
//            val intent = Intent(context, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.flags
////            context.startActivity(intent)
//        }
//    }
//
////    fun getUserDetail(): Map<String, String> {
////        val user: Map<String, String> = HashMap()
////        (user as HashMap)[KEY_EMAIL] = preferences.getString(KEY_EMAIL, null)!!
////        user[KEY_PASSWORD] = preferences.getString(KEY_PASSWORD, null)!!
////        return user
////    }
//
//    fun clickLogout(view : View) {
//        val intent = Intent(context, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        Firebase.auth.signOut()
////        editor.commit()
//        editor.clear()
//        intent.flags
////        logoutUser()
//
//    }
//
//    fun isLoginUser(): Boolean {
//        return preferences.getBoolean(IS_LOGIN, false)
//    }
//}