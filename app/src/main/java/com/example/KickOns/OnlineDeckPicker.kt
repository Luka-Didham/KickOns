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

    override fun switchDeck(){
        val intent = Intent(this,DeckPicker::class.java)
        startActivity(intent)
    }
    override suspend fun getDecks(myCallback: FirebaseCallback) {
        db.collection("Decks")
            .get()
            .addOnSuccessListener { result ->
                val decks = mutableListOf<DeckItem>()
                for (document in result) {
                    val d = DeckItem(null,document.get("name").toString())
                    decks.add(d)
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
                myCallback.onResponse(decks)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
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
    override suspend fun getCards(id: Int?) {
        cardList.clear()
        //TODO get the cards from the db related to the id you pressed
    }

}