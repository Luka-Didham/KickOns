package com.example.colorchanging
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deck_table")
data class DeckItem (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var cards: Collection<CardItem>
)