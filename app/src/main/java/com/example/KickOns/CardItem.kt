package com.example.KickOns

import androidx.room.Entity
import androidx.room.PrimaryKey

var cardList = mutableListOf<CardItem>()

@Entity(tableName = "card_table")
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var cardType: Int,
    var challenge: String,
    var deckId: Int?
)