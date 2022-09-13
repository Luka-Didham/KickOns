package com.example.KickOns

import androidx.room.Entity
import androidx.room.PrimaryKey

var cardList = mutableListOf<CardItem>()

@Entity(tableName = "card_table")
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val cardType: Int,
    var challenge: String,
    val deckId: Int?
)