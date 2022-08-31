package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var deckDao: DeckDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeckPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainActivity = this

        //Speed at which items are deleted
        recyclerView.itemAnimator?.removeDuration = 5
        val swipeGesture = object : SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT ->{
                        delete(deckList[viewHolder.adapterPosition], viewHolder.adapterPosition)
                    }
                }

            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.recyclerView)

        db = CardDB.getDatabase(this)
        deckDao = db.deckDAO()

        GlobalScope.launch{
            getDecks()
            withContext(Dispatchers.Main){
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                    adapter = DeckAdapter(deckList,mainActivity)
                }
            }
        }

        btnCreateDeckFromChoose.setOnClickListener {
            val intent = Intent(this, DeckCreation::class.java)
            startActivity(intent)
        }

    }
    fun delete(deck: DeckItem, pos : Int){
        deckList.remove(deck)
        binding.recyclerView.adapter?.notifyItemRemoved(pos)
        GlobalScope.launch {
            deckDao.deleteDeck(deck.id)
        }
    }

     override fun btnClick(deck: DeckItem) {
         val pos : Int = deckList.indexOf(deck)
         deckList.remove(deck)
         binding.recyclerView.adapter?.notifyItemRemoved(pos)
        GlobalScope.launch {
            deckDao.deleteDeck(deck.id)
        }
    }



    override fun onClick(deck: DeckItem) {
       val intent = Intent(this, EditDeck::class.java)
        GlobalScope.launch{
            //Querry db and wait for response
            getCards(deck.id)
            //On main launch next page
            withContext(Dispatchers.Main){
                startActivity(intent)
            }
        }
    }


    //TODO("This seems stupid,
    // decide if we want global list var or to just query local db")
    private suspend fun getCards(id: Int?) {
        cardList.clear()
        val cards = db.cardDAO().getByDeckId(id)
        cards.forEach {
            cardList.add(it)
        }
    }

    private fun getDecks(){
        deckList.clear()
        for(deck in deckDao.getAll()){
            deckList.add(deck)
        }
    }

}

