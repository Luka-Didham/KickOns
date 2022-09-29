package com.example.KickOns
import androidx.room.*


/**
 * Card Data Access Object
 * an interface used to Query the local room db
 *
 * @see com.example.KickOns.CardItem
 * @see com.example.KickOns.CardDB
 */
@Dao
interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addCard(card: CardItem)

    @Delete(entity = CardItem::class)
    suspend fun delete(card: CardItem)

    @Update
    suspend fun update(card: CardItem)

    @Query("SELECT * FROM card_table")
    suspend fun getAll(): List<CardItem>

    /**
     * Onclick function that removes a player view item from the main view.
     *
     * @param deck_id (Int?) of the deck
     * @return The list of card items contained inside the deck
     * i.e card items with matching deck_ids
     *
     * @see com.example.KickOns.CardItem
     */
    @Query("SELECT * FROM card_table WHERE deckId =:deck_id")
    suspend fun getByDeckId(deck_id:Int?): List<CardItem>
}