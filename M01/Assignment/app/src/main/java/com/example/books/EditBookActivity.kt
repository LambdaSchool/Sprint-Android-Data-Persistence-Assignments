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

        entry_id_label.text = "${entry.id}"
        journal_entry_text.setText("I AAM RIGHT HERE")


        add.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(Book.TAG, entry)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

    }




}