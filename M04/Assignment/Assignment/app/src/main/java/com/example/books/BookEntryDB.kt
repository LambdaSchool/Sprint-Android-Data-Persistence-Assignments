package com.example.books

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookEntryDB : RoomDatabase(){
    abstract fun entriesDao(): BookEntryDAO
}