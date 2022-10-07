package com.cosc345.kickons

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cosc345.kickons.databinding.DeckPickerBinding
import kotlinx.coroutines.*

open class DeckPicker() : AppCompatActivity(), DeckClickListener {
    private lateinit var db: CardDB
    private lateinit var binding: DeckPickerBinding
    private lateinit var cardDao: CardDAO
    private lateinit var deckDao: DeckDAO
    private lateinit var deckSets: Array<MutableList<out Any>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeckPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Elements
        val mainActivity = this
        val recyclerView = binding.recyclerView
        val btnCreateDeckFromChoose = findViewById<Button>(R.id.btnCreateDeckFromChoose)

        val btnHome = findViewById<Button>(R.id.btnOnline)

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
        cardDao = db.cardDAO()

        GlobalScope.launch {
            getDecks(object: FirebaseCallback{
                override fun onResponse(response: Array<MutableList<out Any>>) {
                deckSets = response
                deckList = response[1] as MutableList<DeckItem>
                binding.recyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    Log.d("dl", deckList.size.toString())
                    adapter = DeckAdapter(deckList,mainActivity)
                }
                }

            })

        }

        btnHome.setOnClickListener{
            home()
        }

        btnCreateDeckFromChoose.setOnClickListener {
            val intent = Intent(this, DeckCreation::class.java)

            startActivity(intent)
        }

    }
    fun delete(deck: DeckItem, pos : Int){
        var position = deckSets[1].indexOf(deck)
        deckList.remove(deck)
        deckSets[0].removeAt(position)
        binding.recyclerView.adapter?.notifyItemRemoved(pos)
        GlobalScope.launch {
            deckDao.delete(deck)
        }
    }

    private fun home(){
        val intent = Intent(this,WelcomePage::class.java)
        startActivity(intent)
    }

     override fun edit(deck: DeckItem) {
         val intent = Intent(this, EditDeck::class.java)
            intent.putExtra("id",deck.id)
         val pos = deckSets[1].indexOf(deck)
         val id = deckSets[0][pos]
         GlobalScope.launch {
                getCards(id.toString())
                withContext(Dispatchers.Main){
                    startActivity(intent)
                }
            }

     }

    override fun onClick(deck: DeckItem) {
       val intent = Intent(this, MainActivity::class.java)
        val pos = deckSets[1].indexOf(deck)
        val id = deckSets[0][pos]

        GlobalScope.launch{
            //Query db and wait for response
            getCards(id.toString())
            //On main launch next page
            withContext(Dispatchers.Main){
                intent.putExtra("id",deck.id)
                startActivity(intent)
            }
        }
    }

    open suspend fun getCards(id: String) {
        cardList.clear()
        val cards = db.cardDAO().getByDeckId(id.toInt())
        cards.forEach {
            cardList.add(it)
        }
    }

    fun saveCards(cardItem: CardItem){
        GlobalScope.launch{
            cardDao.addCard(cardItem)
        }
    }
    suspend fun saveDeck(deck: DeckItem): Int {
        return deckDao.addDeck(deck).toInt()

    }

    open suspend fun getDecks(myCallback: FirebaseCallback){
        deckList.clear()
        deckSets = emptyArray()
        val deckIdList = mutableListOf<String>()
        val decks = mutableListOf<DeckItem>()
        for(deck in deckDao.getAll()){
            decks.add(deck)
            deckIdList.add(deck.id.toString())
        }
        val deckSets: Array<MutableList<out Any>> = arrayOf(deckIdList, decks)
        myCallback.onResponse(deckSets)
    }



    open fun getDeckSetsID(deck: DeckItem):String {
        val pos = deckSets[1].indexOf(deck)
        val id = deckSets[0][pos]
        return id.toString()
    }

}

