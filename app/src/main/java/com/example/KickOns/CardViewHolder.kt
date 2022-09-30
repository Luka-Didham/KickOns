package com.example.KickOns

import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.CardItemBinding

/**
 * Custom View holder for card view
 *
 * @param cardCell The empty card cell received from the view
 *
 * @see com.example.KickOns.CardItem
 */
class CardViewHolder(
    private val cardCell: CardItemBinding
) : RecyclerView.ViewHolder(cardCell.root) {

    fun bindCard(card: CardItem){
        cardCell.cardTitle.text = card.challenge
        cardCell.cardChallenge.text = card.cardType.toString()
    }
}