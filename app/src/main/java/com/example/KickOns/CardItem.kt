package com.example.KickOns

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

var cardList = mutableListOf<CardItem>()

@Entity(tableName = "card_table",
    foreignKeys = [ForeignKey(
    entity = DeckItem::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("deckId"),
    onDelete = ForeignKey.CASCADE
)])
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val cardType: Int,
    var challenge: String,
    val deckId: Int?
)