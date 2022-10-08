package com.cosc345.kickons

import android.content.Intent
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * An extension class to DeckPicker.kt which overrides some functionality with firebase database specific
 * functionality.
 *
 */
class OnlineDeckPicker: DeckPicker(), DeckClickListener {
    val db = Firebase.firestore
    private lateinit var deckSets: Array<MutableList<out Any>>

  /**
   * Method which fetches decks from Firebase database
   *
   * @param myCallback Firebase callback
   *
   */
    override fun getDecks(myCallback: FirebaseCallback) {
        db.collection("Decks")
            .get()
            .addOnSuccessListener { result ->
                val deckIdList = mutableListOf<String>()
                val decks = mutableListOf<DeckItem>()
                deckSets = emptyArray()

                for (document in result) {
                    val d = DeckItem(null,document.get("name").toString(),null)
                    decks.add(d)
                    deckIdList.add(document.id)
                }
                deckSets = arrayOf(deckIdList, decks)
                myCallback.onResponse(deckSets)
            }
            .addOnFailureListener { exception ->
            }

    }
  /**
   * Method which registers when a deck element is pressed.
   *
   * @param deck specific DeckItem object pressed
   */
    override fun onClick(deck: DeckItem) {
        val id = getDeckSetsID(deck)
        GlobalScope.launch{
            //Query db and wait for response
            getCards(id)
        }
    }

  /**
   * Method overrides so a standard user cant update online decks
   *
   * @param deck specific DeckItem
   */
    override fun edit(deck: DeckItem) {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
        val id = getDeckSetsID(deck)
        GlobalScope.launch {
            val deckId = saveDeck(deck)

            saveCards(id,deckId)

            // Save an online deck locally
        }

    }

    override fun setOnline(){
        online = true
    }

  /**
   * Method which saves cards
   *
   * @param id id of deck saved
   * @param deckId int version of id
   */
    private fun saveCards(id: String, deckId: Int) {
        db.collection("Decks/$id/Cards")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    val c = CardItem(null, 0, document.data["Challenge"].toString(), deckId)
                    saveCards(c)

                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }


    }

  /**
   * Method which gets cards
   *
   * @param id id of deck saved
   */
    override suspend fun getCards(id: String) {
        cardList.clear()
        val intent = Intent(this, MainActivity::class.java)
        db.collection("Decks/$id/Cards")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    val c = CardItem(null, 0, document.data["Challenge"].toString(), -1)
                    cardList.add(c)

                }
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }


    }

}
