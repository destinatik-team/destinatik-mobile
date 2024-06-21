package com.dicoding.destinatik.core.data.local.preferences.auth

import android.content.Context
import android.content.SharedPreferences

class AuthPreferences(
    context: Context
) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Save token
    fun saveToken(token: String) {
        val editor = preferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    // Get user ID
    fun getUserId(): Int? {
        return preferences.getInt(USER_ID_KEY, -1).takeIf { it != -1 }
    }

    // Save user ID
    fun saveUserId(userId: Int) {
        preferences.edit().putInt(USER_ID_KEY, userId).apply()
    }

    // Get token
    fun getToken(): String? {
        return preferences.getString(TOKEN_KEY, null)
    }

    // Clear token
    fun clearToken() {
        val editor = preferences.edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "auth_prefs"
        private const val TOKEN_KEY = "auth_token"
        private const val USER_ID_KEY = "userId"
    }
}
