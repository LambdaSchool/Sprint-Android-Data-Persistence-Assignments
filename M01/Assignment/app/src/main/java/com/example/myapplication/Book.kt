package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.util.Log

class Book() {
    lateinit var title: String
    lateinit var reasonToRead: String
    lateinit var hasBeenRead: String
    lateinit var id: String

    companion object{
        const val HAS_BEEN_READ = "Has been read"
        const val HAS_NOT_BEEN_READ = "Has not been read"
        const val CSV_STRING_ID = "CSV STRING ID"
    }

    constructor(title: String, reasonToRead: String, hasBeenRead: String, id: String) : this(){
        this.title = title
        this.reasonToRead = reasonToRead
        this.hasBeenRead = hasBeenRead
        this.id = id
    }

    constructor(csvString: String) : this(){
        val s = csvString.split(",")

        title = s[0]
        reasonToRead = s[1]
        hasBeenRead = s[2]
        id = s[3]
    }

    fun toCsvString(): String{
        Log.i("HEre", "$hasBeenRead")
        return "$title,$reasonToRead,${hasBeenRead},$id"
    }
}