package com.example.books

import java.io.Serializable
import java.lang.NumberFormatException

class Book: Serializable {
    companion object {
        const val TAG = "BookEntry Entry"
        const val INVALID_ID = -1
    }


    var id: Int = 0
    var title: String? = null
    var reasonToRead: String? = null
    var hasBeenRead: Boolean?= false


    constructor(id: Int){
        this.id = id
        this.title = " "
        this.reasonToRead = " "
        this.hasBeenRead = false
    }
    constructor(csvString: String){
        val values = csvString.split(", ")
        if (values.size == 5)
            try {
                this.id = Integer.parseInt(values[0])
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }
        this.title = values[0].replace(" ,", ">>")
    }
    internal fun toCSVString(): String{
        return "$id, $title, $reasonToRead, $hasBeenRead"
    }

    override fun toString(): String {
        return "BookEntry Entry(id:$id, title:$title, reason to read: $reasonToRead, was it read before: $hasBeenRead)"
    }

}