package com.example.books

import android.content.Context
import android.content.SharedPreferences
import android.icu.text.CaseMap
import java.io.Serializable
import java.lang.NumberFormatException


//model class for Book


/*3. Create two constructors.
    a. One that accepts and assigns all data members
    b. One that parses a CSV string to an object  */

class Book : Serializable {

    companion object {
        const val TAG = "Book Entry"
        const val INVALID_ID = -1
    }

    var title: String? = null
    var reasonToRead: String? = null
    var hasBeenRead: Boolean = false
    var id: Int = 0


    constructor(id: Int) {
        this.id = id
        this.title = " "
        this.reasonToRead = " "
        this.hasBeenRead = false


    }
    constructor(csvString: String){
        val values = csvString.split(" ,")

        if(values.size == 5)
            try {
                this.id = Integer.parseInt(values[0])
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }
        this.title = values[0].replace(" ,", " >>")

    }
    internal fun toCsvString(): String{
        return String.format(
            "%d, %s, %d, %s, %s",
            id, title, reasonToRead, hasBeenRead
        )
    }

    override fun toString(): String {
        return "Book Entry(id:$id, title:$title, reason to read: $reasonToRead, was it read before: $hasBeenRead)"
    }
}