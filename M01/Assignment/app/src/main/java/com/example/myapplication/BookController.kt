package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.LinearLayout

class BookController {

    private fun buildItemView(bookLayout: LinearLayout?, book: Book) : CustomBookEntry? {
        val context = bookLayout?.context

        if(context != null) {
            val entry = CustomBookEntry(context, book)
            entry.setOnClickListener {
                bookLayout.removeViewAt(book.id.toInt())
                val intent = Intent(context, EditBookActivity::class.java)
                intent.putExtra(MainActivity.BOOK_ID, book.id)
                intent.putExtra(Book.CSV_STRING_ID, book.toCsvString())
                (context as Activity).startActivityForResult(intent, 1)
            }
            return entry
        }
        return null
    }

    fun getBooksView(context: Context): LinearLayout?{
        val bookList = BooksModel().getAllBooks()

        bookList?.let{
            val layout = LinearLayout(context)
            layout.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            layout.orientation = LinearLayout.VERTICAL

            it.forEach {
                layout.addView(buildItemView(layout, Book(it)))
            }
            return layout
        }
        return null
    }

    fun handleEditActivityResult(linearLayout: LinearLayout?, intent: Intent?){
        val s = intent?.getStringExtra(Book.CSV_STRING_ID)
        s?.let{
            linearLayout?.addView(buildItemView(linearLayout, Book(it)))
            SharedPreferencesDao.createEntry(Book(it))
        }
    }

}