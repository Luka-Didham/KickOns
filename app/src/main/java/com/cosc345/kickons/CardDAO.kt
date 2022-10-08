package com.cosc345.kickons
import androidx.room.*


/**
 * Card Data Access Object
 * an interface used to Query the local room db
 *
 * @see com.cosc345.kickons.CardItem
 * @see com.cosc345.kickons.CardDB
 */
@Dao
interface CardDAO {
    /**
     * Adds a card to the db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(card: CardItem)
    /**
     * Deletes card from the db
     */
    @Delete(entity = CardItem::class)
    suspend fun delete(card: CardItem)

    /**
     * Updates card from the db
     */
    @Update
    suspend fun update(card: CardItem)

    /**
     * Returns all cards from the db
     */
    @Query("SELECT * FROM card_table")
    suspend fun getAll(): List<CardItem>

    /**
     * Onclick function that removes a player view item from the main view.
     *
     * @param deck_id (Int?) of the deck
     * @return The list of card items contained inside the deck
     * i.e card items with matching deck_ids
     *
     * @see com.cosc345.kickons.CardItem
     */
    @Query("SELECT * FROM card_table WHERE deckId =:deck_id")
    suspend fun getByDeckId(deck_id:Int?): List<CardItem>
}