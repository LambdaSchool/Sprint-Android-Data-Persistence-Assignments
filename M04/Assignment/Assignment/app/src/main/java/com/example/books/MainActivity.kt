package com.example.books

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.style.UpdateLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Update
import com.example.books.BookData.Companion.createBookEntry
import com.example.books.EntriesViewModel.EntriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference


/*this is the last successful commit where the app worked without any errors.
cannot assign any textviews yet and continue the polish*/


class MainActivity : AppCompatActivity() {
    companion object {
        const val NEW_ENTRY_REQUEST = 2
        const val EDIT_ENTRY_REQUEST = 1
    }

    lateinit var viewModel: EntriesViewModel


    //  private var entryList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting the view model here
        viewModel = ViewModelProviders.of(this).get(EntriesViewModel::class.java)

        button.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, EditBookActivity::class.java)
            val entry = createBookEntry()
            intent.putExtra(Book.TAG, entry)
            startActivityForResult(intent, NEW_ENTRY_REQUEST)
        }
        //    entryList = repo.readAllEntries()
        //    textView.removeAllViews()
        //    entryList.forEach { entry->
        //       textView.addView(createBookEntry(entry))
        //    }
    ReadAllEntriesAsyncTask(this).execute()
    }
    // TODO 22: Extract update functionality
    private fun updateLayout(entries: List<Book>) {
        textView.removeAllViews()
        entries.forEach { entry ->
            textView.addView(createBookEntry(entry))
        }
    }

    private fun createBookEntry(entry: Book): TextView {


        val view = TextView(this@MainActivity)


        view.text = "${entry.id}, ${entry.reasonToRead}, ${entry.title}, ${entry.hasBeenRead}"
        view.setPadding(15, 15, 15, 15)
        view.textSize = 22f
        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == NEW_ENTRY_REQUEST) {
            if (data != null) {
                val entry = data.getSerializableExtra(Book.TAG) as Book
             //   entryList.add(entry)
             //   repo.createEntry(entry)
             //   textView.addView(createBookEntry(entry))
                CreateAsyncTask(viewModel).execute(entry)
            }
        } else if (requestCode == EDIT_ENTRY_REQUEST) {
            if (data != null) {
                val entry = data.getSerializableExtra(Book.TAG) as Book

                UpdateAsyncTask(viewModel).execute()
            //    entryList[entry.id] = entry
            //    repo.updateEntry(entry)
            }
        }
    }
    class CreateAsyncTask(viewModel: EntriesViewModel): AsyncTask<Book, Void, Unit>(){

        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg p0: Book?) {
            if (p0.isNotEmpty()){
                p0[0]?.let {
                    viewModel.get()?.createEntry(it)
                }
            }

        }

    }
    class UpdateAsyncTask(viewModel: EntriesViewModel): AsyncTask<Book, Void, Unit>(){
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg p0: Book?) {
            if (p0.isEmpty()){
                p0[0]?.let {
                    viewModel.get()?.createEntry(it)
                }
            }

        }

    }
    class ReadAllEntriesAsyncTask(activity: MainActivity): AsyncTask<Void, Void, LiveData<List<Book>>?>(){
        private val activity = WeakReference(activity)
        override fun doInBackground(vararg p0: Void?): LiveData<List<Book>>? {
            return activity.get()?.viewModel?.entries

        }

        override fun onPostExecute(result: LiveData<List<Book>>?) {
            activity.get()?.let { act->
                result?.let {entries ->
                    entries.observe(
                        act,
                        Observer<List<Book>> { t->
                            t?.let {
                                act.updateLayout(t)
                            }

                        }
                    )
                }
            }
        }

    }
}



