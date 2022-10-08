package com.cosc345.kickons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosc345.kickons.databinding.AddPlayerItemBinding


/**
 * Addapter adding for addding players to the chipgorup
 */
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

    /**
     * A getter function that returns the number of players registered to play.
     *
     * @return The number of player items in the player list.
     */
    override fun getItemCount(): Int {
        return playerList.size
    }
}