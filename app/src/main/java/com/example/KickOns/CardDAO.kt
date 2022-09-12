package com.example.KickOns
import androidx.room.*

@Dao
interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(card: CardItem)

    @Delete
    suspend fun delete(card: CardItem)

    @Update
    suspend fun update(card: CardItem)

    @Query("SELECT * FROM card_table")
    suspend fun getAll(): List<CardItem>

    @Query("SELECT * FROM card_table WHERE deckId =:deck_id")
    suspend fun getByDeckId(deck_id:Int?): List<CardItem>
}