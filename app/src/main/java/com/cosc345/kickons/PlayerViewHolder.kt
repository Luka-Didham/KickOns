package com.cosc345.kickons

import androidx.recyclerview.widget.RecyclerView
import com.cosc345.kickons.databinding.AddPlayerItemBinding


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