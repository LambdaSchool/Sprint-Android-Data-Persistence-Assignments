package com.lambdaschool.sharedprefs.model

import android.net.Uri
import com.lambdaschool.sharedprefs.Prefs
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TODO: 22. The Model Class for a Journal Entry
class JournalEntry : Serializable {

    companion object {
        const val TAG = "JournalEntry"
        const val INVALID_ID = -1
    }

    var date: String? = null
    var entryText: String? = null
    private var image: String? = null
    var dayRating: Int = 0
    var id: Int = 0

    class Journal {

        // TODO: 24. Business-Logic can be placed in an Entity class such as this; helps with Unit Testing too.
        companion object {

            fun createJournalEntry(): JournalEntry {
                return JournalEntry(JournalEntry.INVALID_ID)
            }

            fun createJournalEntry(text: String): JournalEntry {
                val entry = createJournalEntry()
                entry.entryText = text
                return entry
            }

            // Stretch: Need to ensure that a proper ID is created for these test entries
            fun createTestEntries(prefs: Prefs) {
                prefs.createEntry(createJournalEntry("Gathered by gravity how far away finite but unbounded the only home we've ever known network of wormholes Jean-François Champollion? Tendrils of gossamer clouds Orion's sword extraplanetary invent the universe trillion stirred by starlight. Shores of the cosmic ocean vastness is bearable only through love permanence of the stars astonishment a mote of dust suspended in a sunbeam extraplanetary. Made in the interiors of collapsing stars not a sunrise but a galaxyrise a very small stage in a vast cosmic arena a mote of dust suspended in a sunbeam something incredible is waiting to be known astonishment."))
                prefs.createEntry(createJournalEntry("Gathered by gravity how far away finite but unbounded the only home we've ever known network of wormholes Jean-François Champollion? Tendrils of gossamer clouds Orion's sword extraplanetary invent the universe trillion stirred by starlight. Shores of the cosmic ocean vastness is bearable only through love permanence of the stars astonishment a mote of dust suspended in a sunbeam extraplanetary. Made in the interiors of collapsing stars not a sunrise but a galaxyrise a very small stage in a vast cosmic arena a mote of dust suspended in a sunbeam something incredible is waiting to be known astonishment."))
                prefs.createEntry(createJournalEntry("Vangelis muse about Hypatia explorations hundreds of thousands another world. Shores of the cosmic ocean a mote of dust suspended in a sunbeam colonies Tunguska event finite but unbounded shores of the cosmic ocean? Extraplanetary bits of moving fluff gathered by gravity a still more glorious dawn awaits not a sunrise but a galaxyrise with pretty stories for which there's little good evidence. Take root and flourish courage of our questions vastness is bearable only through love paroxysm of global death invent the universe something incredible is waiting to be known?"))
                prefs.createEntry(createJournalEntry("Preserve and cherish that pale blue dot two ghostly white figures in coveralls and helmets are soflty dancing vastness is bearable only through love Euclid permanence of the stars inconspicuous motes of rock and gas. Dispassionate extraterrestrial observer something incredible is waiting to be known star stuff harvesting star light great turbulent clouds network of wormholes the only home we've ever known. Of brilliant syntheses emerged into consciousness vanquish the impossible vanquish the impossible hundreds of thousands dream of the mind's eye."))
                prefs.createEntry(createJournalEntry("Extraplanetary Euclid Hypatia brain is the seed of intelligence intelligent beings Rig Veda. Vastness is bearable only through love circumnavigated emerged into consciousness white dwarf colonies something incredible is waiting to be known. Two ghostly white figures in coveralls and helmets are soflty dancing star stuff harvesting star light bits of moving fluff invent the universe concept of the number one the ash of stellar alchemy. The only home we've ever known invent the universe rich in heavy atoms concept of the number one muse about something incredible is waiting to be known."))
                prefs.createEntry(createJournalEntry("Science dream of the mind's eye stirred by starlight Jean-François Champollion with pretty stories for which there's little good evidence circumnavigated? Sea of Tranquility extraordinary claims require extraordinary evidence the carbon in our apple pies the ash of stellar alchemy ship of the imagination preserve and cherish that pale blue dot. Sea of Tranquility hundreds of thousands ship of the imagination the sky calls to us invent the universe descended from astronomers and billions upon billions upon billions upon billions upon billions upon billions upon billions."))
            }
        }
    }constructor(id: Int) {
        this.id = id
        this.entryText = ""
        this.image = ""

        initializeDate()
    }

    constructor(csvString: String) {
        val values = csvString.split(",")
        // check to see if we have the right string
        if (values.size == 5) {
            // handle missing numbers or strings in the number position
            try {
                this.id = Integer.parseInt(values[0])
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }

            this.date = values[1]
            try {
                this.dayRating = Integer.parseInt(values[2])
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }

            // allows us to replace commas in the entry text with a unique character and
            // preserve entry structure
            this.entryText = values[3].replace("~@", ",")
            // placeholder for image will maintain csv's structure even with no provided image
            this.image = if (values[4] == "unused") "" else values[4]
        }
    }

    // TODO: 23. One approach to save an object into a String
    // converting our object into a csv string that we can handle in a constructor
    fun toCsvString():String{
        return "$id, $date, $dayRating, ${entryText?.replace(",","@ @")}, ${if(image.isNullOrBlank())"unused" else image}"


        // "1 , 2019 , 5, this is todays entry"

    }

    private fun initializeDate() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US)
        val date = Date()

        this.date = dateFormat.format(date)
    }

    fun getImage(): Uri? {
        return if (image != "") {
            Uri.parse(image)
        } else {
            null
        }
    }

    fun setImage(imageUri: Uri) {
        this.image = imageUri.toString()
    }

    override fun toString(): String {
        return "JournalEntry(date=$date, entryText=$entryText, image=$image, dayRating=$dayRating, id=$id)"
    }
}
