package com.example.KickOns

import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.DeckItemBinding


class DeckViewHolder(
    private val deckCell: DeckItemBinding
    ) : RecyclerView.ViewHolder(deckCell.root) {

        fun bindDeck(deck: DeckItem){
            deckCell.deckTitle.text = deck.name
        }
}