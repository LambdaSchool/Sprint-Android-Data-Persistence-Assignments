package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.size
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val BOOK_ID = "BOOK ID"
    }

    val bookController = BookController()
    var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPreferencesDao.sharedPreferences = getSharedPreferences("ENTRIES", Context.MODE_PRIVATE)
        linearLayout = bookController.getBooksView(this)
        scroll_book_entry.addView(linearLayout)

        btn_add.setOnClickListener {
            linearLayout?.let {
                val intent = Intent(this, EditBookActivity::class.java)
                intent.putExtra(BOOK_ID, it.childCount.toString())
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
           bookController.handleEditActivityResult(linearLayout, data)
        } else{
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
