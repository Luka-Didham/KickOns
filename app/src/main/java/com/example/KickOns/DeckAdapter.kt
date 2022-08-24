package com.example.KickOns

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.KickOns.databinding.DeckItemBinding

class DeckAdapter(private val decks: List<DeckItem>)
    : RecyclerView.Adapter<DeckViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = DeckItemBinding.inflate(from, parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        holder.bindDeck(decks[position])
    }

    override fun getItemCount(): Int = decks.size

}