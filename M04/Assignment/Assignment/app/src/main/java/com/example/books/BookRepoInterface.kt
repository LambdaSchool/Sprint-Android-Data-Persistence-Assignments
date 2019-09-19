package com.example.books

import androidx.lifecycle.LiveData

interface BookRepoInterface{

    fun createEntry(entry: Book)
    fun readAllEntries(): LiveData<List<Book>>
    fun updateEntry(entry: Book)
}