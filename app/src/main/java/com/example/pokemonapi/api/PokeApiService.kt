package com.example.pokemonapi.api

import DetailApiResponse
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeApiService {


    @GET("api/v2/pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokeApiResponse

    @GET("api/v2/pokemon/{pokeId}")
    fun getDetails(
            @Path("pokeId") pokeId : String
    ): Call<DetailApiResponse>

    companion object {
        private const val BASE_URL = "https://pokeapi.co/"
        val imageUrlThumb = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

        fun create(): PokeApiService {
            Log.d("PokeApiService","API service created")
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeApiService::class.java)
        }

        fun getImageUrl (pokeId: String): String{
            return "${imageUrl}${pokeId}.png"
        }

        fun getThumbImageUrl (pokeId: String): String{
            return "${imageUrlThumb}${pokeId}.png"
        }
    }
}