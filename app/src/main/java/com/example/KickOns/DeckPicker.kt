package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.DeckPickerBinding
import kotlinx.coroutines.*

class DeckPicker() : AppCompatActivity(), DeckClickListener {
    private lateinit var db: CardDB
    private lateinit var binding: DeckPickerBinding
    private lateinit var deckDao: DeckDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeckPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Elements
        val mainActivity = this
        val recyclerView = binding.recyclerView
        val btnCreateDeckFromChoose = findViewById<Button>(R.id.btnCreateDeckFromChoose)

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
            deckDao.delete(deck)
        }
    }

     override fun edit(deck: DeckItem) {
         val intent = Intent(this, EditDeck::class.java)
            GlobalScope.launch {
                getCards(deck.id)
                withContext(Dispatchers.Main){
                    startActivity(intent)
                }
            }

     }



    override fun onClick(deck: DeckItem) {
       val intent = Intent(this, MainActivity::class.java)
        val d = deck.id
        GlobalScope.launch{
            //Querry db and wait for response
            getCards(deck.id)
            //On main launch next page
            withContext(Dispatchers.Main){
                intent.putExtra("id",deck.id)
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

    private fun getDecks(){
        deckList.clear()
        for(deck in deckDao.getAll()){
            deckList.add(deck)
        }
    }

    private fun editDeck(d: DeckItem){
        val intent = Intent(this, EditDeck::class.java)

    }

}

