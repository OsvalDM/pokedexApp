package com.example.kotllin.pokedexapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PokedexObject (
    @SerializedName("count") val count: Int,
    @SerializedName("results") val result: ArrayList<PokemonBase>,
)