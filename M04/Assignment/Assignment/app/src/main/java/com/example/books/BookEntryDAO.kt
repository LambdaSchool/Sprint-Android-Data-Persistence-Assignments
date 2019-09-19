package com.example.books

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookEntryDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createEntry(entry: Book)

    @Query("select * from Data")
    fun readAllEntries(): LiveData<List<Book>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEntry(entry: Book)

    @Delete
    fun deleteEntry(entry: Book)





}