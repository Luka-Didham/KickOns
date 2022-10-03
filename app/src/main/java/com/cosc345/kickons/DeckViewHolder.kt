package com.cosc345.kickons

import androidx.recyclerview.widget.RecyclerView
import com.cosc345.kickons.databinding.DeckItemBinding



class DeckViewHolder(
    private val deckCell: DeckItemBinding,
    private val clickListener: DeckClickListener

    ) : RecyclerView.ViewHolder(deckCell.root) {

        fun bindDeck(deck: DeckItem){
            deckCell.deckTitle.text = deck.name

            //TODO(Add deck subtitle)

            deckCell.editBtn.setOnClickListener{
                clickListener.edit(deck)
            }
            deckCell.cardView.setOnClickListener {
                clickListener.onClick(deck)

            }
        }
}