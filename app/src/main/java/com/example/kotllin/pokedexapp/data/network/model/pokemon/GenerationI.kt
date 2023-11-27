package com.example.kotllin.pokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") val red_blue: com.example.kotllin.pokedexapp.data.network.model.pokemon.RedBlue,
    val yellow: com.example.kotllin.pokedexapp.data.network.model.pokemon.Yellow
)