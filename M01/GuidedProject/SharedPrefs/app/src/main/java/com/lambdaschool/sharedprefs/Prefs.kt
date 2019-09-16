package com.lambdaschool.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import com.lambdaschool.sharedprefs.model.JournalEntry
import java.lang.StringBuilder

// TODO: 15. A Shared Preferences helper class

class Prefs (context: Context) {


    // TODO: 16. KEYS for Shared Preferences can be defined as Constants here
    companion object{
        //key for shared prefs
        private const val JOURNAL_PREFS = "Journal Prefs"
        //key for ID_LISt
        private const val ID_LIST_KEY = "id_list"
        // key for entry item
        private const val NEXT_ID = "next_id"
        //store key
        private const val STORE_KEY_PREFIX = "store key"
    }
    val sharedPrefs: SharedPreferences = context.getSharedPreferences(JOURNAL_PREFS, Context.MODE_PRIVATE)

    // TODO: 17. Each Journal Entry will be its own entry in shared preferences
    // create a new entry
    fun createEntry(entry: JournalEntry){
        //look at the list of ids
        val ids = getListofIDs()

        if(entry.id == JournalEntry.INVALID_ID && !ids.contains(entry.id.toString())){
            val editor = sharedPrefs.edit()

            var nextId = sharedPrefs.getInt(NEXT_ID, 0)

            entry.id = nextId
            // we increment id so we dont create entries with same id
            editor.putInt(NEXT_ID, nextId++)


            ids.add(entry.id.toString())
            //store
            val newIDList = StringBuilder()
            //string builder
            for(id in ids){
                newIDList.append(id).append(", ")
            }
            //editor put the string in
            editor.putString(ID_LIST_KEY, newIDList.toString())
            //store it
            editor.putString(STORE_KEY_PREFIX + entry.id.toString(), entry.toCsvString())
            editor.commit()
        }else{
            updateEntry(entry)
        }
        //CONVERT TO CSV STRING step 23

    }
    // TODO: 18. IDs are all stored as a CSV list in one SharedPreferences entry
    private fun getListofIDs(): ArrayList<String> {
        //storing a list of IDS
        val idList = sharedPrefs.getString(ID_LIST_KEY,"") ?: " "
        // split it
        val oldList = idList?.split(" ,")
        //set up ids
        val ids = ArrayList<String>(oldList.size)
        if(idList.isNotBlank()){
            ids.addAll(oldList)
        }
        return ids
    }
    // TODO 18A read an existing entry
    fun readALL(id: Int): JournalEntry{
        val entryCSV = sharedPrefs.getString(STORE_KEY_PREFIX + id, "invalid") ?: " "
        return entryCSV?.let {
            JournalEntry(entryCSV)
        }
        }
    // TODO: 19. This collects all known entries in Shared Preferences, with the help of the ID List
    // read all entries
    fun readALL(): MutableList<JournalEntry>{

        val listOfIDs = getListofIDs()
        val entryList = mutableListOf<JournalEntry>()
        for(id in listOfIDs){
            entryList(id.toInt())?.let {
                entryList.add(it)
            }
        }

    return mutableListOf()
    }

    // TODO: 20. This is another way to define a SharedPreferences item
    // In Activity, can simply use: prefs.bgColor (to get and set)

    // TODO: 21. Update an entry - use CSV technique to "serialize" a Journal Entry
    // edit an existing entry
    fun updateEntry(entry: JournalEntry){

        val editor = sharedPrefs.edit()
        editor.putString(STORE_KEY_PREFIX + entry.id, entry.toCsvString())
        editor.commit()

    }


}