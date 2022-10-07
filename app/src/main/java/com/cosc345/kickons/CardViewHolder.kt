package com.cosc345.kickons

import androidx.recyclerview.widget.RecyclerView
import com.cosc345.kickons.databinding.CardItemBinding

/**
 * Custom View holder for card view
 *
 * @param cardCell The empty card cell received from the view
 *
 * @see com.cosc345.kickons.CardItem
 */
class CardViewHolder(
    private val cardCell: CardItemBinding
) : RecyclerView.ViewHolder(cardCell.root) {

    fun bindCard(card: CardItem){
        cardCell.cardTitle.text = card.challenge
        cardCell.cardChallenge.text = card.cardType.toString()
    }
}