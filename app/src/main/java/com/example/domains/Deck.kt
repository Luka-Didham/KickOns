package com.example.domains

data class Deck(
    val name: String,
    val creator: String,
    val cards: MutableList<Card>,
)



