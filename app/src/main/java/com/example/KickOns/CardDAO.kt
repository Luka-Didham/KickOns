package com.example.KickOns
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(card: CardItem)

    @Query("SELECT * FROM card_table")
    fun getAll(): List<CardItem>

    @Query("SELECT * FROM card_table WHERE deckId =:deck_id")
    suspend fun getByDeckId(deck_id:Int?): List<CardItem>
}