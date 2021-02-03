package com.example.pokemonapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapi.data.Pokemon
import com.example.pokemonapi.repo.PokemonRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PokemonViewModel (private val repository: PokemonRepo): ViewModel(){

    private var currentSearchResult: Flow<PagingData<Pokemon>>? = null

    fun searchPokemons(): Flow<PagingData<Pokemon>> {

        val newResult: Flow<PagingData<Pokemon>> = repository.getSearchResultStream()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}