package com.example.KickOns

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditDeck(): AppCompatActivity() {
    private lateinit var db : CardDB

    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        db = CardDB.getDatabase(this)
        setContentView(R.layout.activity_deck_edit)
        getCards(0);

        val cardText = findViewById<EditText>(R.id.editCard)
        cardText.setText(cardList[0].challenge)
    }

    private fun getCards(id: Int){
        GlobalScope.launch {
            val cards = db.cardDAO().getByDeckId(1)
            withContext(Dispatchers.Main){
                cards.forEach{
                    cardList.add(it)
                }
            }
        }

    }
}