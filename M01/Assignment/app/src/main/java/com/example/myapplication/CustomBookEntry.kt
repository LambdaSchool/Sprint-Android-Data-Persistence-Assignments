package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.widget.TextView

class CustomBookEntry(context: Context, val book: Book) : TextView(context){

    init{
        text = book.title
        setBackgroundColor(Color.RED)
        setTextColor(Color.WHITE)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        setPadding(10, 0, 10, 0)
    }
}