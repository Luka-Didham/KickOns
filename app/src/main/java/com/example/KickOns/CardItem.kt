package com.example.KickOns

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val cardType: Int,
    val challenge: String,
    val deckId: Int?
)