package com.example.books

import android.content.Context
import android.content.SharedPreferences
import java.lang.StringBuilder

class Prefs (context: Context) {
    companion object {
        private const val BOOK_PREFERENCE = "Book Preference!"
        private const val ID_LIST_KEY = "id_list"
        private const val ENTRY_ITEM_KEY_PREFIX = "entry_"
        private const val NEXT_ID_KEY = "next_id"
    }

    val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(BOOK_PREFERENCE, Context.MODE_PRIVATE)

    fun createEntry(entry: Book) {
        val ids = getListofIDs()
        if (entry.id == Book.INVALID_ID && !ids.contains(entry.id.toString())) {
            val editor = sharedPrefs.edit()

            var nextID = sharedPrefs.getInt(NEXT_ID_KEY, 0)
            entry.id = nextID
            editor.putInt(NEXT_ID_KEY, ++nextID)

            ids.add(Integer.toString(entry.id))
            val newIDList = StringBuilder()
            for (id in ids) {
                newIDList.append(id).append(", ")
            }
            editor.putString(ID_LIST_KEY, newIDList.toString())
            editor.apply()
        } else {
            updateEntry(entry)
        }
    }

    private fun getListofIDs(): java.util.ArrayList<String> {
        val idList = sharedPrefs.getString(ID_LIST_KEY, " ")
        val oldList = idList!!.split(", ")

        val ids = ArrayList<String>(oldList.size)
        if (idList.isNotBlank()) {
            ids.addAll(oldList)
        }
        return ids
    }

    fun readOneEntry(id: Int): Book {
        val entryCSV = sharedPrefs.getString(ENTRY_ITEM_KEY_PREFIX + id, "invalid") ?: " "
        return entryCSV?.let {
            Book(entryCSV)
        }

    }

    fun readAllEntries(): MutableList<Book> {
        val listOfIDs = getListofIDs()

        val entryList = java.util.ArrayList<Book>()
        for (id in listOfIDs){
            if (id.isNotBlank()){
                readOneEntry(id.toInt())?.let {
                    entryList.add(it)
                }
            }
        }
        return entryList

    }
    fun updateEntry(entry: Book){
        val editor = sharedPrefs.edit()
        editor.putString(ENTRY_ITEM_KEY_PREFIX + entry.id, entry.toCSVString())
        editor.apply()
    }
}