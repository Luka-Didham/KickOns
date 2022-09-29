package com.example.KickOns

/**
 * Interface handling click events for the deck
 * in the deck picker activity
 *
 * @see com.example.KickOns.DeckPicker
 */
interface DeckClickListener {

    /**
     * Onclick function that starts the game witht the selected deck
     *
     * @param deck The current view in the app
     * @see com.example.KickOns.MainActivity
     * @see com.example.KickOns.DeckPicker
     */
    fun onClick(deck: DeckItem)
    fun edit(deck: DeckItem)
}