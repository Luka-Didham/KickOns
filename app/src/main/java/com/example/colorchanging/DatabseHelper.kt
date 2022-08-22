package com.example.colorchanging

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;

class DatabseHelper(context: Context) : SQLiteOpenHelper(context,DB_NAME,null, DB_VERSION) {

    companion object{
        private val DB_NAME = "deck"
        private val DB_VERSION = 1
        private val TABLE_NAME = "decks"
        private val ID = "id"
        private val DECK_NAME = "deckname"
        private val CARDS = "cards"
        private val CREATOR  = "creator"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Decide if cards should be stored as JSON or or as a seperate sql table")
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INT PRIMARY KEY, $DECK_NAME TEXT, $CARDS TEXT, $CREATOR TEXT)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }

    fun getAllDecks(): LIST<DeckItem>{
        val deckList = ArrayList<DeckItem>()
        val db = writeableDatabse
        val selectQuery = "SELECT *FROM $TABLE_NAME"
        val cursor = db.rawQuerry(selectQuery.null)
        if(cursosr != null){
            if(cursor.moveToFirst()){
                do {
                    val decks = DeckItem()
                    decks.id = Interger.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    decks.name = cursor.getString(cursor.getCollumnIndex(TABLE_NAME))
                }
            }
        }
    }

}