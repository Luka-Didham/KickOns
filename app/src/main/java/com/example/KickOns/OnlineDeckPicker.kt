package com.example.KickOns

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.KickOns.databinding.DeckPickerBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
}