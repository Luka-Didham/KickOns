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

            //TODO(Add deck subtitle)
            if(!online){
                deckCell.editBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.edit_icon,0,0,0)
                deckCell.editBtn.visibility = View.INVISIBLE
            } else {
                deckCell.editBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.add,0,0,0)
            }
            deckCell.editBtn.setOnClickListener{
                clickListener.edit(deck)
            }
            deckCell.cardView.setOnClickListener {
                clickListener.onClick(deck)

            }
        }
}