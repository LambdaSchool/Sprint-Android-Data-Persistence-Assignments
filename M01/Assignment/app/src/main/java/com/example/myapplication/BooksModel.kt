package com.example.myapplication

class BooksModel {

    fun getAllBooks(): ArrayList<String>?{
        return SharedPreferencesDao.getAllEntries()
    }

    fun getBookById(id: String): Book? {
        val s = (SharedPreferencesDao.getEntry(id))
        if(s == SharedPreferencesDao.ID_NOT_FOUND){
            return null
        } else{
            return Book(s)
        }
    }

    fun updateBook(book: Book){
        return SharedPreferencesDao.updateEntry(book)
    }
}