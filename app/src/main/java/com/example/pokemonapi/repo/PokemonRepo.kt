package com.example.pokemonapi.repo

import DetailApiResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokemonapi.api.PokeApiService
import com.example.pokemonapi.data.Pokemon
import com.example.pokemonapi.data.PokemonPagingSource
import retrofit2.Callback
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Response

class PokemonRepo (private val service: PokeApiService) {



    fun getSearchResultStream(): Flow<PagingData<Pokemon>> {

        return Pager(
            PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        ) {
            PokemonPagingSource(service)
        }.flow
    }

    fun getDetails(pokeId: String) : MutableLiveData<DetailApiResponse>  {

        val detailResponse = MutableLiveData<DetailApiResponse>()
        val call = service.getDetails(pokeId)
        call.enqueue( object : Callback<DetailApiResponse> {
            override fun onResponse(call: Call<DetailApiResponse>, response: Response<DetailApiResponse>) {
                detailResponse.value = response.body()
            }

            override fun onFailure(call: Call<DetailApiResponse>, t: Throwable) {
                //TODO show a notification in UI
            }
        })
        return detailResponse
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}