package com.example.KickOns

import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.DeckItemBinding



class DeckViewHolder(
    private val deckCell: DeckItemBinding,
    private val clickListener: DeckClickListener,

    ) : RecyclerView.ViewHolder(deckCell.root) {

        fun bindDeck(deck: DeckItem){
            deckCell.deckTitle.text = deck.name
            deckCell.cardView.setOnClickListener {
                clickListener.onClick(deck)

            }
        }
}