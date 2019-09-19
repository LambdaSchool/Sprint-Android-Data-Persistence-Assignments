package com.example.books

interface BookRepoInterface{

    fun createEntry(entry: Book)
    fun readAllEntries(): MutableList<Book>
    fun updateEntry(entry: Book)
}