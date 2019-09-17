package com.example.books

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var entryList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


        private fun createBookEntry(entry: Book): TextView {


            val view = TextView(this@MainActivity)


            view.text = "${entry.id}, ${entry.reasonToRead}, ${entry.title}, ${entry.hasBeenRead}"
            view.setPadding(15, 15, 15, 15)
            view.textSize = 22f
            return view


        }


    }

