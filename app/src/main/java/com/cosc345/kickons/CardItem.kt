package com.cosc345.kickons

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
/**
 * Data class for cards
 * @param id the cards id
 * @param cardType the type of card i.e challenge,law,etc..
 * @param challenge The text on the card
 * @param deckId the deck the id leads to
 */
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var cardType: Int,
    var challenge: String,
    var deckId: Int?
)