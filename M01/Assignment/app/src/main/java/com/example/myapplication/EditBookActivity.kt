package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    var idString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        idString = intent.getStringExtra(MainActivity.BOOK_ID)

        button_submit.setOnClickListener {
            returnData() //only goes back to main activity if theres input
        }

        button_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
    }

    private fun returnData(){
        if(edit_title.text.toString() != ""){
            idString?.let {
                val book = Book(
                    edit_title.text.toString(), edit_reason.text.toString(),
                    if (checkBox.isChecked) Book.HAS_BEEN_READ else Book.HAS_NOT_BEEN_READ, it)

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Book.CSV_STRING_ID, book.toCsvString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        returnData() //if it doesnt go back to main activity, just go back, same as cancel essentially
        super.onBackPressed()
    }
}
