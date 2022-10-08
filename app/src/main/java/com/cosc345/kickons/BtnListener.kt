package com.cosc345.kickons

/**
 * Interface for edit button on deck items
 */
interface BtnListener {
    /**
     * Sends user to edit activity for the specified deck
     *
     * @param deck the DeckItem you want to edit
     */
    fun edit(deck: DeckItem)
}