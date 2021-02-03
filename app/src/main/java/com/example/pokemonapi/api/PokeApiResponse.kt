package com.example.pokemonapi.api

import com.example.pokemonapi.data.Pokemon
import com.google.gson.annotations.SerializedName

class PokeApiResponse (
    @SerializedName("count") val total: Int = 0,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val items: List<Pokemon> = emptyList()
)