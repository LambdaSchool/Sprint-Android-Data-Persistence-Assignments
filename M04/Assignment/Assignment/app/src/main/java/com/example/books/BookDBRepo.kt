package com.example.books

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

class BookDBRepo (context: Context) : BookRepoInterface{
    override fun createEntry(entry: Book) {
            database.entriesDao().createEntry(entry)
    }

    override fun readAllEntries(): LiveData<List<Book>> {
        return database.entriesDao().readAllEntries()
    }

    override fun updateEntry(entry: Book) {
        database.entriesDao().updateEntry(entry)
    }
    override fun deleteEntry(entry: Book){
        database.entriesDao().deleteEntry(entry)
    }


    private val context2 = context.applicationContext


    // TODO 1 build room database
    private val database by lazy {
        Room.databaseBuilder(
            context2, BookEntryDB::class.java, "entry_database"
        ).fallbackToDestructiveMigration().build()
    }
}
