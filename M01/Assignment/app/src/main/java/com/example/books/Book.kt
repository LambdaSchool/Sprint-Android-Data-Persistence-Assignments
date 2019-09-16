package com.example.books

class Book{
    companion object{
        fun createBookEntry(): BookEntry{
            return BookEntry(BookEntry.INVALID_ID)
        }
        fun createBookEntry1(text: String): BookEntry{
            val entry = createBookEntry()
            entry.title = text
            return entry
        }
    }
}