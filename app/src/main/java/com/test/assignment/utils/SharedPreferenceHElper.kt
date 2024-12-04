package com.test.assignment.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHElper {
    private lateinit var sharedpreferences: SharedPreferences
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    private fun SharedPreferenceHelper(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SESSION_SP, Context.MODE_PRIVATE)
        editor = sharedpreferences.edit()
    }

    companion object{
            const val SESSION_SP = "SESSION_SP"
    }

}