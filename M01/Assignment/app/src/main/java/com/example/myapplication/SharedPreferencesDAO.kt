package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class SharedPreferencesDAO(activity: AppCompatActivity, prefName: String) {

    var sharedPreferences: SharedPreferences = activity.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    companion object{
        const val BOOK_ENTRY = "BOOK_ENTRY"
        const val ID_NOT_FOUND = "NOT FOUND"
    }

    fun createEntry(book: Book){
        val editor = sharedPreferences.edit()
        editor.putString(BOOK_ENTRY + book.id, book.toCsvString())
        editor.apply()
    }

    fun updateEntry(book: Book){ //modifies the entry for this book
        //check if this book exists in shared preferences, if it does, modify it, if it doesn't then we will create this entry
        createEntry(book)
    }

    fun getEntry(id: String): String{ //so if the string returned is null, then it will create a null-pointer error, if its
        return sharedPreferences.getString(BOOK_ENTRY+id, ID_NOT_FOUND) ?: ID_NOT_FOUND
    }

    fun getAllEntries(): ArrayList<String>? {
        val entryMap = sharedPreferences.all //returns a mutable map of all string value pairs
        if(!entryMap.isNullOrEmpty()) {
            val bookCsvList = ArrayList<String>(entryMap.size)

            for (i in 0..entryMap.size-1) {
                val s = entryMap.get(BOOK_ENTRY + i.toString()) as String
                bookCsvList.add(s)
            }
            return bookCsvList
        } else{
            return null
        }
    }
}