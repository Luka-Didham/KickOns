package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.deck_creation.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DeckCreation() : AppCompatActivity() {

    private var backToMain: MainActivity? = null
    private var confirmName: CardCreation? = null
    private lateinit var db: CardDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_creation)

        db = CardDB.getDatabase(this)
        val deckDao = db.deckDAO()

        val text = findViewById<EditText>(R.id.editDeckName) as EditText

        btnBackFromCreateDeck.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSaveDeck.setOnClickListener {
            val d = DeckItem(null, text.text.toString())
            val intent = Intent(this, CardCreation()::class.java)

            //Saves and sends deck Id to card add screen
            GlobalScope.launch {
                val deckId = async { db.deckDAO().addDeck(d) }
                intent.putExtra("deck_id", deckId.await())
                startActivity(intent)
            }
        }

    }
}