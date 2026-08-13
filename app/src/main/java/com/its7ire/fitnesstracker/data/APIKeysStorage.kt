package com.its7ire.fitnesstracker.data
import android.content.Context
import android.content.SharedPreferences

class ApiKeyStorage(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveKey(key: String) {
        prefs.edit().putString("GEMINI_KEY", key).apply()
    }

    fun getKey(): String? {
        return prefs.getString("GEMINI_KEY", null)
    }

    fun deleteKey() {
        prefs.edit().remove("GEMINI_KEY").apply()
    }
}