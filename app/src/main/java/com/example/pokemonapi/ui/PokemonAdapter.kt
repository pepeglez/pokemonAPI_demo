package com.example.pokemonapi.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapi.data.Pokemon

class PokemonAdapter : PagingDataAdapter<Pokemon, PokemonViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokeItem = getItem(position)
        if (pokeItem != null) {
            holder.bind(pokeItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.url == newItem.url
        }
    }
}