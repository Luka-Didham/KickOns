package com.example.KickOns

import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.AddPlayerItemBinding
import com.example.KickOns.databinding.DeckItemBinding



class PlayerViewHolder(
    private val playerCell: AddPlayerItemBinding,
    //TODO("Change to player listener")
    ) : RecyclerView.ViewHolder(playerCell.root) {

    fun bindPlayer(player: Player){
        playerCell.playerText.text = player.name
//        playerCell.cardView.setOnClickListener {
//            clickListener.onClick(deck)
//
//        }
    }
}