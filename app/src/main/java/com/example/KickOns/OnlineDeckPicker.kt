package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.KickOns.databinding.DeckPickerBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnlineDeckPicker: DeckPicker() {
    val db = Firebase.firestore
    private lateinit var deckSets: Array<MutableList<out Any>>

    override suspend fun getDecks(myCallback: FirebaseCallback) {
        db.collection("Decks")
            .get()
            .addOnSuccessListener { result ->
                val deckIdList = mutableListOf<String>()
                val decks = mutableListOf<DeckItem>()
                deckSets = emptyArray()

                for (document in result) {
                    val d = DeckItem(null,document.get("name").toString())
                    decks.add(d)
                    deckIdList.add(document.id)
                }
                deckSets = arrayOf(deckIdList, decks)
                myCallback.onResponse(deckSets)
            }
            .addOnFailureListener { exception ->
            }

    }
    override fun onClick(deck: DeckItem) {
        val id = getDeckSetsID(deck)
        GlobalScope.launch{
            //Querry db and wait for response
            getCards(id)
        }
    }
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