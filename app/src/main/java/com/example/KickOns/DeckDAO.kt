package com.example.KickOns

import androidx.room.*

@Dao
interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeck(deck: DeckItem) : Long

    @Query("SELECT * FROM deck_table")
    fun getAllWithCards(): List<DeckWithCards>

    @Query("SELECT * FROM deck_table")
    fun getAll(): List<DeckItem>

//    @Query("DELETE FROM deck_table where ID = :deck_id")
//    fun deleteById(deck_id: Int)


    @Query("SELECT * FROM deck_table where ID = :deck_id")
    fun getById(deck_id: Int): List<DeckWithCards>

    @Query("DELETE FROM deck_table WHERE ID = :deck_id")
    suspend fun deleteDeck(deck_id : Int?)

}