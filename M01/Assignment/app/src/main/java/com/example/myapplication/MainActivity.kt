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

        val b = Book("The Great Gatsby", "Cool",Book.HAS_BEEN_READ, "123")
        val b1 = Book("Harry Potter", "Nerdy", Book.HAS_BEEN_READ, "124")
        val b2 = Book("The Chronicles of Riddick", "Exciting", Book.HAS_NOT_BEEN_READ, "36")

//        layout_book_entry.addView(Book.buildItemView(b, this))
//        layout_book_entry.addView(Book.buildItemView(b1, this))
//        layout_book_entry.addView(Book.buildItemView(b2, this))

        btn_add.setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra(BOOK_ID, layout_book_entry.childCount.toString())
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("HI", "HERE")
        if(resultCode == Activity.RESULT_OK){
            Log.i("GOT ", "HERE")
            val s = data?.getStringExtra(Book.CSV_STRING_ID)
            s?.let{
                layout_book_entry.addView(Book.buildItemView(Book(it), this))
            }
        } else{
            Toast.makeText(this, "Not able to add book", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}
