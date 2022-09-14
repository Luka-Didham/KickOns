package com.example.KickOns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_player_item.view.*

class AddPlayerAdapter(
    var players: List<Player>
): RecyclerView.Adapter<AddPlayerAdapter.PlayerViewHolder>(){
    inner class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_player_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.itemView.playerText.text = players[position].name

    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}