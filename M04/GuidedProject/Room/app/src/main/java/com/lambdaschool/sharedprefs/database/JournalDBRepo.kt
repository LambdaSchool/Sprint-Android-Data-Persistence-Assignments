package com.lambdaschool.sharedprefs.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lambdaschool.sharedprefs.JournalRepoInterface
import com.lambdaschool.sharedprefs.model.JournalEntry

// TODO 5: Create the Database repo and implement the methods
// we dont have database as of yet
class JournalDBRepo(context: Context) : JournalRepoInterface{
    private val context2 = context.applicationContext

    override fun createEntry(entry: JournalEntry) {
        //
        database.entriesDao().createEntry(entry)
    }

    override fun readAllEntries(): LiveData<List<JournalEntry>> {

        return database.entriesDao().readAllEntries()
    }

    override fun updateEntry(entry: JournalEntry) {
        database.entriesDao().updateEntry(entry)
    }

    override fun deleteEntry(entry: JournalEntry) {
        database.entriesDao().deleteEntry(entry)
    }
    // TODO 15: Build the Room database
    //lazy is like lateinit,
    private val database by lazy {
        Room.databaseBuilder(
            context2,
            JournalEntryDB::class.java, "entry_database"
        ).fallbackToDestructiveMigration().build()//if its not the same version drop the data and make new data
    }

}



