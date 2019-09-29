package com.example.books.EntriesViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.books.Book
import com.example.books.repo

// TODO 24: Create a ViewModel for entries
class EntriesViewModel : ViewModel() {

    // TODO 25: Create a LiveData object for the entries
    val entries: LiveData<List<Book>> by lazy {
        readAllEntries()
    }



    // TODO 26: Recreate the repo calls to as functions here.


    fun readAllEntries(): LiveData<List<Book>> {

        return  repo.readAllEntries()
    }
    fun createEntry(entry: Book) {
        //
        repo.createEntry(entry)
    }

    fun updateEntry(entry: Book) {
        repo.updateEntry(entry)
    }

  //  fun deleteEntry(entry: Book) {
 //       repo.deleteEntry(entry)
   // }

}