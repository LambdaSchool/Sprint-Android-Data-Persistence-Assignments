package com.lambdaschool.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Environment
import android.os.Environment.DIRECTORY_DCIM
import com.lambdaschool.sharedprefs.model.JournalEntry
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.StringBuilder

// TODO 3: Implement the interface here
class JournalFileRepo(var context: Context): JournalRepoInterface {
    override fun updateEntry(entry: JournalEntry) {
    }

    override fun deleteEntry(entry: JournalEntry) {
    }

    // Basic structure: We will save each object to its own json file

    // TODO 6: createEntry implementation
    override fun createEntry(entry: JournalEntry) {
        val entryString = entry.toJsonObject()
        val filename = entry.date + ".json"
        writeToFile(filename, entryString.toString())
    }

    // TODO 8: writeToFile helper
    private fun writeToFile(filename: String, entryString: String){
        //figure out directory
        //open file input stream
        //write
        //close
        val dir = storageDirectory
        //thats a file
        val outputFile = File(dir, filename)
        var writer: FileWriter? = null
        //try to open the file
        try {
            writer = FileWriter(outputFile)
            writer.write(entryString)
        }catch (e: IOException){
            e.printStackTrace()
        }finally {
            //closing the writer
            if (writer != null){
                try {
                    writer.close()
                }catch (e2: IOException){
                    e2.printStackTrace()
                }
            }
        }

    }
    val storageDirectory: File
        get() {
            if (isExternalStorageWritable) {
                val directory = context.filesDir

                //make sure the file exists or make it
                return if (!directory.exists() && !directory.mkdir()) {
                    context.cacheDir
                } else {
                    return directory
                }
            }else{
             return   context.cacheDir
            }
        }
    // TODO 9: Save storage directory as a member variable

    // TODO 10: Check for external storage is writeable
    val isExternalStorageWritable: Boolean
    get() {
        // get the state from the environment
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }

    override fun readAllEntries(): MutableList<JournalEntry> {
       // get a fileList, set up array list, read files and convert to objects
        val entries = ArrayList<JournalEntry>()
        //read the files in
        for (filename in fileList){
            val json = readFromFile(filename)
            try{
                entries.add(JournalEntry(JSONObject(json)))
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }
        return entries

    }
    // TODO 12: Save fileList as a member variable
    //file lists are generally strings
    val fileList: ArrayList<String>
    get() {
        //return an array list
        val fileNames = arrayListOf<String>()
        val director = storageDirectory
        //looking for json file
        val list   = director.list()
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
    //pass in a file
    private fun readFromFile(filename: String): String {
        val inputFile = File(storageDirectory, filename)
        var readString: String? = null
        //set up string builder
     //   val readData = StringBuilder()
        //set up text
        var reader: FileReader? = null
        try {
            reader = FileReader(inputFile)

            //got the reader then do something with it if its not -1 appen and read until end of file
            readString = reader.readText()
      //      while (next != -1){
      //          readData.append(next)
      //          next = reader.read()
      //      }

        }catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }finally {
            if(reader != null){
                try{
                    reader.close()
                }catch (e: IOException){
                    e.printStackTrace()
                }
            }
        }
       // return readData.toString()
        return readString ?: " "
    }
    // TODO 14: updateEntry implementation

    // TODO 15: deleteEntry implementation


}