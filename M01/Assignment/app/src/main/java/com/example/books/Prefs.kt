package com.example.books

import android.content.Context
import android.content.SharedPreferences
import java.lang.StringBuilder

class Prefs(context: Context){
    companion object{
        private const val BOOK_PREFS = "BookEntry prefs"
        private const val NEXT_ID_KEY = "Next ID"
        private const val ID_LIST_KEY = "Id list key"
        private const val ENTRY_PREFIX = " entry prefix_"
    }
    // val for sharedPrefs
    val sharedPrefs: SharedPreferences = context.getSharedPreferences(BOOK_PREFS, Context.MODE_PRIVATE)

    fun createBookEntry(entry: BookEntry){
        //read list of ids
        val ids = getListofIDS()
        if (entry.id == BookEntry.INVALID_ID && !ids.contains(entry.id.toString())){
            //new entry
            val editor = sharedPrefs.edit()
                var nextID = sharedPrefs.getInt(NEXT_ID_KEY, 0)
            entry.id = nextID
            //store the id
            editor.putInt(NEXT_ID_KEY, ++nextID)


            //add it to the list
            ids.add(Integer.toString(entry.id))
            //create a new list
            val newList = StringBuilder()
            for (id in ids){
                newList.append(id).append(" ,")
            }
            editor.putString(ID_LIST_KEY, newList.toString())
            editor.putString(ENTRY_PREFIX + entry.id, entry.toCsvString() )
            editor.apply()
        }else{
            updateEntry(entry)
        }
    }
    fun getListofIDS(): java.util.ArrayList<String>{
        val idList = sharedPrefs.getString(ID_LIST_KEY, " ")
        val oldList = idList!!.split(" ,")
        val ids = ArrayList<String>(oldList.size)
        if(idList.isNotBlank()){
            ids.addAll(oldList)
        }
        return ids
    }
    //read an existing entry
    fun readExisting(id: Int): BookEntry{
        val entryCsv = sharedPrefs.getString(ENTRY_PREFIX + id, "invalid") ?: " "
        return entryCsv?.let {
            BookEntry(entryCsv)
        }
    }
    //read all entries
    fun readALL():MutableList<BookEntry>{
        //read the list of ids
        val listofIDS = getListofIDS()
        //step through and read each entry
        val entryList =java.util.ArrayList<BookEntry>()
        for (id in listofIDS) {
            if (id.isNotBlank()) {
                readExisting(id.toInt())?.let {
                    entryList.add(it)
                }
            }
        }
            return entryList
        }

    fun updateEntry(entry: BookEntry){
        val editor = sharedPrefs.edit()
        editor.putString(ENTRY_PREFIX + entry.id, entry.toCsvString())
        editor.apply()
    }




}