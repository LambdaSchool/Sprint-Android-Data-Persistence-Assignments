package com.example.books

class BookEntry{
    companion object{
        fun createBookEntry():Book{
            return Book(Book.INVALID_ID)
        }

        fun createBookEntry(text: String):Book {
            val entry = createBookEntry()
            entry.title = text
            return entry
        }
    }
}