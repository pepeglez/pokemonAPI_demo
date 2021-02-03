package com.example.pokemonapi.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapi.ItemListActivity
import com.example.pokemonapi.R
import com.example.pokemonapi.api.PokeApiService
import com.example.pokemonapi.data.Pokemon

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.tv_name)
    private val img: ImageView = view.findViewById(R.id.img_pokemon)

    private var pokemon: Pokemon? = null

    init {
        view.setOnClickListener {

            (it.context as ItemListActivity).showDetails(pokemon!!)

        }
    }

    fun bind(pokemon: Pokemon?) {
        if (pokemon == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.app_name)
        } else {
            showPokeData(pokemon)
        }
    }

    private fun showPokeData(pokemon: Pokemon) {
        this.pokemon = pokemon
        name.text = pokemon.name

        val url = "${PokeApiService.imageUrlThumb}${pokemon.getId()}.png"
        Glide.with(itemView)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
            .fallback(R.mipmap.ic_launcher_round)
            .into(img)
    }

    companion object {
        fun create(parent: ViewGroup): PokemonViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return PokemonViewHolder(view)
        }

    }
}