package com.example.kotllin.pokedexapp.domain

import com.example.kotllin.pokedexapp.data.PokemonRepository
import com.example.kotllin.pokedexapp.data.network.model.pokemon.Pokemon

class PokemonInfoRequirement {
    private val repository = PokemonRepository()

    suspend operator fun invoke(
        numberPokemon:Int
    ): Pokemon? = repository.getPokemonInfo(numberPokemon)
}