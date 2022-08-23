package com.example.KickOns

import androidx.room.*

@Dao
interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeck(deck: DeckItem)

    @Transaction
    @Query("SELECT * FROM deck_table")
    fun getAll(): List<DeckItem>

}