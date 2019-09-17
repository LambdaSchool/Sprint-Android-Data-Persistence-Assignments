package com.example.myapplication

object SharedPreferencesDAO {

    const val ID_LIST = ""
    const val NEXT_ID = ""

    fun getAllBookIds(){
        
    }

    fun getNextId(): String {
        return MainActivity.sharedPreferences?.getString(NEXT_ID, "N/A") ?: "N/A"
    }
}