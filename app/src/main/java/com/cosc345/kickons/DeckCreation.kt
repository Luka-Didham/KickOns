package com.cosc345.kickons

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DeckCreation() : AppCompatActivity() {

    private var backToMain: DeckPicker? = null
    private var confirmName: CardCreation? = null
    private lateinit var db: CardDB
    private lateinit var deckDao: DeckDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deck_creation)

        db = CardDB.getDatabase(this)
        deckDao = db.deckDAO()

        val deckName = findViewById<EditText>(R.id.editDeckName)
        //val deckDesc = findViewById<EditText>(R.id.editDeckDescription)
        //Elements
        val btnBackFromCreateDeck = findViewById<Button>(R.id.btnBackFromCreateDeck)
        val btnSaveDeck = findViewById<Button>(R.id.btnSaveDeck)



        btnBackFromCreateDeck.setOnClickListener {
            val intent = Intent(this, DeckPicker::class.java)
            startActivity(intent)
        }

        btnSaveDeck.setOnClickListener {
            val d = DeckItem(null, deckName.text.toString())//, deckDesc.text.toString())
            val intent = Intent(this, EditDeck()::class.java)
            deckList.add(d)

            //Saves and sends deck Id to card add screen
            GlobalScope.launch {
                val deckId = async { db.deckDAO().addDeck(d) }
                val id = deckId.await().toInt()
                getCards(id)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        }

    }

    private suspend fun getCards(id: Int?) {
        cardList.clear()
        val cards = db.cardDAO().getByDeckId(id)
        cards.forEach {
            cardList.add(it)
        }
    }
}