package com.example.books

import android.content.Context
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONException
import org.json.JSONObject
import java.io.*

//TODO 3: Imlement the repo interface into the new book repo class

class BookRepo (var context: Context): BookRepoInterface{

    //TODO 4: added all members and implementing create entry now
    override fun createEntry(entry: Book) {
        val entryString = entry.toJsonObject() // at my step 4 tojson object is not created yet, done at step 7
        //creating a file name
        val filename = entry.title + ".json"
        writeToFile(filename, entryString.toString())
    }
    // TODO 5 (r.8) writing writeToFile helper method
    private fun writeToFile(filename: String, entryString: String){
        //figure out the directory
        val dir = storageDirectory // storage directory not configured yet as of this step, configured later in code few lines below
        // now the file
        val outputFile = File(dir, filename)
        var writer: FileWriter? = null
        //trying to open the file
        try {
            //write the file
            writer = FileWriter(outputFile)
            writer.write(entryString)
        }catch (e: IOException){
            e.printStackTrace()
        }finally {
            //close the writer always in finally block
            if (writer != null){
                try {
                    writer.close()
                }catch (e2: IOException){
                    e2.printStackTrace()
                }
            }

        }


    }
    // TODO 9: Save storage directory as a member variable
    private val storageDirectory: File
    get() {
        if (isExternalStorageWritable){
            val directory = context.filesDir
            //make sure the file exists or make it
            return if (!directory.exists() && !directory.mkdir()){
                context.cacheDir
            }else{
                return directory
            }
        }else{
            return context.cacheDir
        }
    }
    // TODO 10: Check for external storage is writeable
    private val isExternalStorageWritable: Boolean
    get() {
        //get the state from the environment you re working on
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }


    override fun readAllEntries(): LiveData<List<Book>> {
        //TODO 10 B : get a file list and set up an array for that list
        val entries = ArrayList<Book>()
        for (filename in fileList) { //fileList will be saved as member var few lines later
            val json = readFromFile(filename) //same as fileList(will be passed later as helper fun)
            try {
                val jsonObject = JSONObject(json)
                val element = Book(jsonObject)
                entries.add(element)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val liveData = MutableLiveData<List<Book>>()
        liveData.postValue(entries)
        return liveData
    }
    // TODO 12 Save the list as member var
    // returns file list as string
    val fileList: ArrayList<String>
    get() {
        //return an array list
        val fileNames = arrayListOf<String>()
        val directory2 = storageDirectory
        //look for the json ext files
        val list = directory2.list()
        if (list != null){
            for (name in list){
                if (name.contains(".json")){
                    fileNames.add(name)
                }
            }
        }
        return fileNames
    }
    // TODO 13: readFromFile helper
    //pass in the file
    private fun readFromFile(filename: String):String{
        val inputFile = File(storageDirectory, filename)
        //set up the text
        var readString: String? = null
        var reader: FileReader? = null
        try {
            reader = FileReader(inputFile)
            readString = reader.readText()
        }catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }finally {
            if (reader != null){
                try {
                    reader.close()
                }catch (e: IOException){
                    e.printStackTrace()
                }
            }
        }
        return readString ?: ""
    }

    override fun updateEntry(entry: Book) {

            createEntry(entry)
    }

}