package com.virtualstudios.dihilt.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppUserPreference @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
    }

    fun saveName(name: String) {
        sharedPreferences.edit {
            putString(KEY_NAME, name)
        }
    }

    fun saveEmail(email: String) {
        sharedPreferences.edit {
            putString(KEY_EMAIL, email)
        }
    }

    fun getName(): String? = sharedPreferences.getString(KEY_NAME, null)

    fun getEmail(): String? = sharedPreferences.getString(KEY_EMAIL, null)

}