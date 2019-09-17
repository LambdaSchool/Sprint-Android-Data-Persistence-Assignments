package com.example.books

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.books.BookData.Companion.createBookEntry
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val NEW_ENTRY_REQUEST = 2
        const val EDIT_ENTRY_REQUEST = 1
    }


    private var entryList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, EditBookActivity::class.java)
            val entry = createBookEntry()
            intent.putExtra(Book.TAG, entry)
            startActivityForResult(intent, NEW_ENTRY_REQUEST)
        }


    }

        private fun createBookEntry(entry: Book): TextView {


            val view = TextView(this@MainActivity)


            view.text = "${entry.id}, ${entry.reasonToRead}, ${entry.title}, ${entry.hasBeenRead}"
            view.setPadding(15, 15, 15, 15)
            view.textSize = 22f
            return view

        }
    }



