package com.example.books

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_book.*


class EditBookActivity : AppCompatActivity() {

    private var entry: Book = Book(Book.INVALID_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)


        val intent = intent
       entry = intent.getSerializableExtra(Book.TAG) as Book

        entry_id_label.setText("${entry.id}")
        journal_entry_text.setText("${entry.title}, ${entry.reasonToRead}, ${entry.hasBeenRead}")


        add.setOnClickListener {
            val resultIntent = Intent()

            var wholeEntry = journal_entry_text.text.toString().split(",")
            entry.title = wholeEntry[0].trim()
            entry.reasonToRead = wholeEntry[1].trim()
            entry.hasBeenRead = wholeEntry[2].trim().toBoolean()
            entry.id = entry_id_label.text.toString().trim().toInt()
            resultIntent.putExtra(Book.TAG, entry)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

    }




}