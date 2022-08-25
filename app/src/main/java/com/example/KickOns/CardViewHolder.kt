package com.example.KickOns

import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.CardItemBinding


class CardViewHolder(
    private val cardCell: CardItemBinding
) : RecyclerView.ViewHolder(cardCell.root) {

    fun bindCard(card: CardItem){
        cardCell.cardTitle.text = card.challenge
        cardCell.cardChallenge.text = card.cardType.toString()
    }
}