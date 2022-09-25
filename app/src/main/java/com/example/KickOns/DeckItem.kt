package com.example.KickOns

import androidx.room.Entity
import androidx.room.PrimaryKey

var deckList = mutableListOf<DeckItem>()
@Entity(tableName = "deck_table")
data class DeckItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name: String
    //val description: String
)