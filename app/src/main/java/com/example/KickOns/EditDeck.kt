package com.example.KickOns

import android.os.Bundle
import android.provider.Settings
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_deck_edit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditDeck(): AppCompatActivity() {
    private lateinit var db : CardDB
    private lateinit var cardDao: CardDAO
    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)

        db = CardDB.getDatabase(this)
        cardDao = db.cardDAO()
        //Position of card in deck
        var pos = 0

        setContentView(R.layout.activity_deck_edit)
        //Get Cards for specified deck
        //TODO("Parse in extra context of deck id from precious view")
        val id = intent.extras?.getInt("id")
        getCards(id!!);

        val cardText = findViewById<EditText>(R.id.editCard)
        nextCard(cardText, pos);


        btn_next.setOnClickListener {
            pos = posInc(pos)
            nextCard(cardText, pos)

        }

        btn_prev.setOnClickListener {
            pos = posDec(pos)
            nextCard(cardText, pos)

        }


        del_card.setOnClickListener {
            GlobalScope.launch {
                cardDao.delete(cardList[pos])
            }
            cardList.removeAt(pos)
            pos = posInc(pos);
            nextCard(cardText, pos)

        }

        sv_crd.setOnClickListener {
            cardList[pos].challenge = cardText.text.toString()
            GlobalScope.launch {
                cardDao.update(cardList[pos])

                withContext(Dispatchers.Main) {
                    pos = posInc(pos);
                    nextCard(cardText, pos)
                }
            }
        }
    }





    private fun posDec(pos: Int) : Int {
        if (pos - 1 < 0) return cardList.size - 1
        return pos-1
    }

    private fun posInc(pos : Int): Int {
        if (pos+1 == cardList.size) return 0
        return pos+1
    }



    private fun nextCard(text: EditText, pos : Int){
            text.setText(cardList[pos].challenge)
    }

    private fun getCards(id: Int){
        GlobalScope.launch {
            val cards = db.cardDAO().getByDeckId(id)
            withContext(Dispatchers.Main){
                cards.forEach{
                    cardList.add(it)
                }
            }
        }

    }
}