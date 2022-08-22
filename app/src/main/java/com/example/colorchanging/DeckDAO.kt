package com.example.colorchanging
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeck(deck: DeckItem)

    @Query("SELECT * FROM deck_table")
    fun getDecks(): LiveData<List<DeckItem>>

}