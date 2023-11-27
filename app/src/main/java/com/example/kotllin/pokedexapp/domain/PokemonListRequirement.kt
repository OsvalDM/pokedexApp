package com.example.kotllin.pokedexapp.domain

import com.example.kotllin.pokedexapp.data.PokemonRepository
import com.example.kotllin.pokedexapp.data.network.model.PokedexObject

class PokemonListRequirement {
    private val repository = PokemonRepository()

    suspend operator fun invoke(
        limit:Int
    ): PokedexObject? = repository.getPokemonList(limit)
}