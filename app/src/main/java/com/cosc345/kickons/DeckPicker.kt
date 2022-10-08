package com.cosc345.kickons

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

    /**
     * Function called upon creation of the DeckPicker Intent.
     * Displays the DeckPicker.xml layout as the current activity,
     * and readies the activity with all decks and cards.
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnline()
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
            /**
             * Function to find if a deck entry has been swiped to be deleted,
             * and deletes the swiped object.
             */
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

                    GlobalScope.launch(Dispatchers.Main){
                        binding.recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                            Log.d("dl", deckList.size.toString())
                            adapter = DeckAdapter(deckList,mainActivity)
                        }
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

    /**
     * Deletes a deck from the deck display screen, called by the onSwiped function.
     *
     */
    fun delete(deck: DeckItem, pos : Int){
        var position = deckSets[1].indexOf(deck)
        deckList.remove(deck)
        deckSets[0].removeAt(position)
        binding.recyclerView.adapter?.notifyItemRemoved(pos)
        GlobalScope.launch {
            deckDao.delete(deck)
        }
    }

    /**
     * Defines where the home button will take you onClick.
     */
    private fun home(){
        val intent = Intent(this,WelcomePage::class.java)
        startActivity(intent)
    }

    /**
     * Takes the user to the editing page for whichever deck they selected.
     */
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

    /**
     * Function to let the program know if the decks being viewed are local or online
     * @see com.cosc345.kickons.DeckViewHolder
     */
    open fun setOnline(){
        online = false
    }

    /**
     * Takes the activity to the main page, displaying the deck's cards
     * @see com.cosc345.kickons.MainActivity
     */
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

    /**
     * Called to populate the cards to be shown in the MainActivity.kt activity.
     */
    open suspend fun getCards(id: String) {
        cardList.clear()
        val cards = db.cardDAO().getByDeckId(id.toInt())
        cards.forEach {
            cardList.add(it)
        }
    }

    /**
     * Function called by the saveDeck function to save a deck's cards.
     */
    fun saveCards(cardItem: CardItem){
        GlobalScope.launch{
            cardDao.addCard(cardItem)
        }
    }

    /**
     * A function to save a deck from the online database to local storage.
     */
    suspend fun saveDeck(deck: DeckItem): Int {
        return deckDao.addDeck(deck).toInt()
    }

    /**
     * Queries the saved decks to populate the screen with the saved decks.
     */
    open fun getDecks(myCallback: FirebaseCallback){
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

    /**
     * Function to find a deck's ID in order to link up a deck title to its cards.
     */
    open fun getDeckSetsID(deck: DeckItem):String {
        val pos = deckSets[1].indexOf(deck)
        val id = deckSets[0][pos]
        return id.toString()
    }
}
