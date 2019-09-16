package com.example.books

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            val entry = BookEntry() as Book
            intent.putExtra(Book.TAG, entry)
            startActivityForResult(intent, NEW_ENTRY_REQUEST)
        }
        entryList = prefs?.readALL()
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

    private fun createBookEntry(entry: Book): TextView {
        val view = TextView(this@MainActivity)
        view.text = "${entry.id}, ${entry.reasonToRead}, ${entry.title}, ${entry.hasBeenRead}"
        view.setPadding(15, 15, 15, 15)
        view.textSize = 22f

        view.setOnClickListener {
            val viewDetailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            viewDetailIntent.putExtra(Book.TAG, entry)
            startActivityForResult(viewDetailIntent, EDIT_ENTRY_REQUEST)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode== Activity.RESULT_OK){
            if (requestCode== NEW_ENTRY_REQUEST){
                if (data != null){
                    val entry = data.getSerializableExtra(Book.TAG) as Book
                    entryList.add(entry)
                    prefs.updateEntry(entry)
                }
            }else if(requestCode== EDIT_ENTRY_REQUEST){
                if (data != null){
                    val entry = data.getSerializableExtra(Book.TAG) as Book
                    entryList[entry.id] = entry
                    prefs.updateEntry(entry)
                }
            }
        }
    }


}
