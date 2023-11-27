package com.example.kotllin.pokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: com.example.kotllin.pokedexapp.data.network.model.pokemon.Icons,
    @SerializedName("ultra-sun-ultra-moon") val ultra_sun_ultra_moon: com.example.kotllin.pokedexapp.data.network.model.pokemon.UltraSunUltraMoon
)