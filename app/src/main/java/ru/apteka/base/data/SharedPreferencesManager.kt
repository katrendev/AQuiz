package ru.apteka.base.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) : ISharedPreferenceManager {

    companion object {
        const val APTEKA_PREFS = "apteka_prefs"
    }

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(APTEKA_PREFS, Context.MODE_PRIVATE)
    }
}