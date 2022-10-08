package com.cosc345.kickons

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosc345.kickons.databinding.DeckItemBinding


var online = false

class DeckViewHolder(
    private val deckCell: DeckItemBinding,
    private val clickListener: DeckClickListener

    ) : RecyclerView.ViewHolder(deckCell.root) {

        fun bindDeck(deck: DeckItem){
            deckCell.deckTitle.text = deck.name
            deckCell.deckSub.text = deck.description

            //TODO(Add deck subtitle)
            if(!online){
                deckCell.editBtn.setImageResource(R.drawable.edit_icon)
            } else {
                deckCell.editBtn.setImageResource(R.drawable.download)
            }
            deckCell.editBtn.setOnClickListener{
                clickListener.edit(deck)
                deckCell.editBtn.visibility = View.INVISIBLE
            }
            deckCell.cardView.setOnClickListener {
                clickListener.onClick(deck)
            }
        }
}