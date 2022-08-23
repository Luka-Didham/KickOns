package com.example.KickOns

import androidx.room.Embedded
import androidx.room.Relation

data class  DeckWithCards (
    @Embedded val deck: DeckItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "deckId"
    )
    val cards: List<CardItem>
)
