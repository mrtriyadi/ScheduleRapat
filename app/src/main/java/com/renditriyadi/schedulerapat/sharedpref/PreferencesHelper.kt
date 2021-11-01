package com.renditriyadi.schedulerapat.sharedpref

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper (context: Context){

    private val prefsName = "login123"
    private val sharedpref: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedpref.edit()

    fun put(key: String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String) : String {
        return sharedpref.getString(key, null)?:""
    }

    fun put(key: String, value: Boolean){
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String) : Boolean{
        return sharedpref.getBoolean(key, false)
    }

    fun clear(){
        editor.clear()
            .apply()
    }

}