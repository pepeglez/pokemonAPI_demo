package com.example.pokemonapi.data

import androidx.paging.PagingSource
import com.example.pokemonapi.api.PokeApiService
import com.example.pokemonapi.repo.PokemonRepo.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PokemonPagingSource(
    private val service: PokeApiService
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {

            val response = service.fetchPokemonList(params.loadSize, offset = if (position > 1) (((position - 1) * params.loadSize)+1) else position)
            val pokemonList = response.items
            val nextKey = if (pokemonList.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                pokemonList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )

        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}