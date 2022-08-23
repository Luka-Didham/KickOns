package com.example.KickOns

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddPlayer : AppCompatActivity() {
    private var backToMain: MainActivity? = null



    private var confirmName : CardCreation? = null
    private lateinit var db : CardDB

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_creation)

        db = CardDB.getDatabase(this)
        val deckDao = db.deckDAO()

        val text = findViewById<EditText>(R.id.editDeckName) as EditText

    }
}