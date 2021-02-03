package com.example.pokemonapi.data

import com.google.gson.annotations.SerializedName

class Pokemon (
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String
    ){

    fun getId () = url.split("/")[6]
}