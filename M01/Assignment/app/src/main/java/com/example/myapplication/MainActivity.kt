package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var sharedPreferences: SharedPreferences? = null
        const val BOOK_ID = "BOOK ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("entry details", Context.MODE_PRIVATE)

        btn_add.setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra(BOOK_ID, layout_book_entry.childCount.toString())
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK){
            val s = data?.getStringExtra(Book.CSV_STRING_ID)
            s?.let{
                layout_book_entry.addView(buildItemView(Book(it), this))
            }
        } else{
        }
        
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun buildItemView(book: Book, context: Context) : CustomBookEntry{
        val entry = CustomBookEntry(context, book)
        entry.setOnClickListener {
            layout_book_entry.removeViewAt(book.id.toInt())
            val intent = Intent(context, EditBookActivity::class.java)
            intent.putExtra(MainActivity.BOOK_ID, book.id)
            intent.putExtra(Book.CSV_STRING_ID, book.toCsvString())
            context.startActivity(intent)
        }
        return entry
    }
}
