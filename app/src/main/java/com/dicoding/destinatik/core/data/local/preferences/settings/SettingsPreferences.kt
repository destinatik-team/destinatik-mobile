package com.dicoding.destinatik.core.data.local.preferences.settings

import android.content.Context
import android.content.SharedPreferences

class SettingsPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "settings"
        private const val DARK_MODE = "dark_mode"
    }

    fun setDarkMode(isDarkMode: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(DARK_MODE, isDarkMode)
        editor.apply()
    }

    fun isDarkMode(): Boolean {
        return preferences.getBoolean(DARK_MODE, false)
    }
}