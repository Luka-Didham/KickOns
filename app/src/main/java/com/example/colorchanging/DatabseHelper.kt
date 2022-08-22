package com.example.colorchanging

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;

class DatabseHelper(context: Context) : SQLiteOpenHelper(context,DB_NAME,null, DB_VERSION) {

    companion object {
        private val DB_NAME = "deck"
        private val DB_VERSION = 1
        private val TABLE_NAME = "decks"
        private val ID = "id"
        private val DECK_NAME = "deckname"
        private val CARDS = "cards"
        private val CREATOR = "creator"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Decide if cards should be stored as JSON or or as a seperate sql table")
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($ID INT PRIMARY KEY, $DECK_NAME TEXT)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }

    fun getAllDecks(): List<DeckItem>{
        val deckList = ArrayList<DeckItem>()
        val db = writeableDatabse
        val selectQuery = "SELECT *FROM $TABLE_NAME"
        val cursor = db.rawQuerry(selectQuery, null)
        if (cursosr != null) {
            if (cursor.moveToFirst()) {
                do {
                    val deck = DeckItem()
                    deck.deckID = Interger.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    deck.name = cursor.getString(cursor.getCollumnIndex(TABLE_NAME))
                    TODO("Change from string to collection/json")
                    //deck.cards = cursor.getString(CARDS)
                    deckList.add(deck)
                } while (cursor.moveToNext())

            }
        }
        cursor.close()
        return deckList
    }

    //Insert
    fun addDeck(decks: DeckItem): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DECK_NAME, decks.name)
        //values.put(CARDS, decks.cards)
        db.close()
        return (Integer.parseInt("$_sucsess") != -1)
    }

    //Get Deck
    fun getCard(_id: Int): DeckItem{
        val deck = DeckItem()
        val db = writeableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERER $ID = $_id"
        val cursor = db.rawQuerry(selectQuery, null)

        cursor?.moveTofirst()
        deck.deckID = Interger.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        deck.name = cursor.getString(cursor.getCollumnIndex(TABLE_NAME))
        cursor.close()
        return decks
    }

    //Delete Deck
    fun deleteDeck(_id: Int) : Boolean{
        val db = this.writeableDatabase
        val _success = db.delete(TABLE_NAME, "$ID=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    //Update

    fun updateDeck(deck: DeckItem) : Boolean {
        val db = this.writeableDatabase
        val values = ContentValues()
        values.put(DECK_NAME, deck.name)
        val _success: Int = db.update(TABLE_NAME, values, "$ID=?", arrayOf(deck.id.toString()))
        db.close()
        return Integer.parseInt("$_success") != -1
    }
}