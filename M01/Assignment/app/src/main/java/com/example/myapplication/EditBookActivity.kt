package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val idInt = getIntent().getStringExtra(MainActivity.BOOK_ID)


        button_submit.setOnClickListener {
            returnData()
        }

        button_cancel.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun returnData(){
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        returnData()
    }


}
