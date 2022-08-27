package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.KickOns.databinding.DeckPickerBinding
import kotlinx.android.synthetic.main.deck_creation.*
import kotlinx.android.synthetic.main.deck_item.*
import kotlinx.android.synthetic.main.deck_picker.*
import kotlinx.coroutines.*

class DeckPicker() : AppCompatActivity(), DeckClickListener,BtnListener {

    private var playWithDefaultDeck: AddPlayer? = null
    private var confirmName: CardCreation? = null
    private lateinit var db: CardDB
    private lateinit var binding: DeckPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeckPickerBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val mainActivity = this
        db = CardDB.getDatabase(this)
        val deckDao = db.deckDAO()

        GlobalScope.launch{
            withContext(Dispatchers.Main){
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                    adapter = DeckAdapter(deckList,mainActivity, mainActivity)
                }
            }
        }

        btnCreateDeckFromChoose.setOnClickListener {
            val intent = Intent(this, DeckCreation::class.java)
            startActivity(intent)
        }

    }

     override fun btnClick(deck: DeckItem) {
         val pos : Int = deckList.indexOf(deck)
        GlobalScope.launch {
            db.deckDAO().deleteDeck(deckList.indexOf(deck))
        }
         deckList.remove(deck)
         binding.recyclerView.adapter?.notifyItemRemoved(pos)
    }



    override fun onClick(deck: DeckItem) {
       val intent = Intent(this, MainActivity::class.java)
        GlobalScope.launch{
            //Querry db and wait for response
            getCards(deck.id)

            //On main launch next page
            withContext(Dispatchers.Main){
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

