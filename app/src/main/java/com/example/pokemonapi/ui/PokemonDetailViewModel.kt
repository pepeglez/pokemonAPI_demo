package com.example.pokemonapi.ui

import DetailApiResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapi.data.Pokemon
import com.example.pokemonapi.repo.PokemonRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PokemonDetailViewModel (private val repository: PokemonRepo): ViewModel(){

    private var details: MutableLiveData<DetailApiResponse>? = null

    fun getDetails(pokeId: String): MutableLiveData<DetailApiResponse>? {

        viewModelScope.launch {
            details = repository.getDetails(pokeId)
        }
        return details
    }
}