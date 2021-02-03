package com.example.pokemonapi

import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapi.api.PokeApiService
import com.example.pokemonapi.repo.PokemonRepo
import com.example.pokemonapi.ui.ViewModelFactory

object Injection {

    private fun providePokemonRepository(): PokemonRepo {
        return PokemonRepo(PokeApiService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(providePokemonRepository())
    }
}