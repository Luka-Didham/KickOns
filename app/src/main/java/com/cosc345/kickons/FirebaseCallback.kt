package com.cosc345.kickons

interface FirebaseCallback {
    fun onResponse(response: MutableList<DeckItem>)
}