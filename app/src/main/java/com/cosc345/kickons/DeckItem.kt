package com.cosc345.kickons

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.FileDescriptor

var deckList = mutableListOf<DeckItem>()
@Entity(tableName = "deck_table")

/**
 * Deck which holds specific deck item
 *
 */
data class DeckItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val description: String?
)
