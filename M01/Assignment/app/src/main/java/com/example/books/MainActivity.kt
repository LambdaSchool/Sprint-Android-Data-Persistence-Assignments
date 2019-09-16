package com.example.books

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.books.Book.Companion.createBookEntry
import com.example.books.Book.Companion.createBookEntry1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NEW_ENTRY_REQUEST = 2
        const val EDIT_ENTRY_REQUEST = 1



    }

    private var entryList = mutableListOf<BookEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            val entry = createBookEntry()
            intent.putExtra(BookEntry.TAG, entry)
            startActivityForResult(intent, NEW_ENTRY_REQUEST)
        }
        entryList = prefs.readALL()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        textView.removeAllViews()
        entryList.forEach {entry->
            textView.addView(createBookEntry(entry))
        }

    }

    private fun createBookEntry(entry: BookEntry): TextView {
        val view = TextView(this@MainActivity)
        view.text = "${entry.id}, ${entry.reasonToRead}, ${entry.title}, ${entry.hasBeenRead}"
        view.setPadding(15, 15, 15, 15)
        view.textSize = 22f

        view.setOnClickListener {
            val viewDetailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            viewDetailIntent.putExtra(BookEntry.TAG, entry)
            startActivityForResult(viewDetailIntent, EDIT_ENTRY_REQUEST)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode== Activity.RESULT_OK){
            if (requestCode== NEW_ENTRY_REQUEST){
                if (data != null){
                    val entry = data.getSerializableExtra(BookEntry.TAG) as BookEntry
                    entryList.add(entry)
                    prefs.updateEntry(entry)
                }
            }else if(requestCode== EDIT_ENTRY_REQUEST){
                if (data != null){
                    val entry = data.getSerializableExtra(BookEntry.TAG) as BookEntry
                    entryList[entry.id] = entry
                    prefs.updateEntry(entry)
                }
            }
        }
    }


}
