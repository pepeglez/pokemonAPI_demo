package com.example.pokemonapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapi.repo.PokemonRepo

class ViewModelFactory(private val repository: PokemonRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PokemonDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}