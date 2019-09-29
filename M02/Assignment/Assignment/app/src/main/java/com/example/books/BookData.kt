package com.example.books

class BookData {

    companion object{
        fun createBookEntry(): Book {
            return Book(Book.INVALID_ID)
        }
        fun createBookEntry(text: String): Book{
            val entry = createBookEntry()
            entry.title = text
            return entry
        }
        fun createTestData(prefs: Prefs){
            prefs.createEntry(createBookEntry("Test Data"))
            prefs.createEntry(createBookEntry("Test Data Two!"))
        }
    }
}