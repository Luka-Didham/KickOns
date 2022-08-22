package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_creation.*
import kotlinx.android.synthetic.main.deck_creation.*

class DeckCreation : AppCompatActivity() {

    private var backToMain: MainActivity? = null
    private var confirmName : CardCreation? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_creation)

        val text = findViewById<EditText>(R.id.editDeckName) as EditText

        btnBackFromCreateDeck.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSaveDeck.setOnClickListener {
            val intent = Intent(this, CardCreation::class.java)
//          INSERT CODE FOR SAVING DECK INSTANCE AND NAME HERE
            startActivity(intent)
        }

    }
}