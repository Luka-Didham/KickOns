package com.example.KickOns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.AddPlayerItemBinding


class AddPlayerAdapter(
    var players: List<Player>
): RecyclerView.Adapter<PlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = AddPlayerItemBinding.inflate(from,parent,false)

        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindPlayer(players[position])


    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}