package com.cosc345.kickons

/**
 * Interface handling click events for the deck
 * in the deck picker activity
 *
 * @see com.cosc345.kickons.DeckPicker
 */
interface DeckClickListener {

    /**
     * Onclick function that starts the game with the selected deck
     *
     * @param deck The current view in the app
     * @see com.cosc345.kickons.MainActivity
     * @see com.cosc345.kickons.DeckPicker
     */
    fun onClick(deck: DeckItem)
    fun edit(deck: DeckItem)
}