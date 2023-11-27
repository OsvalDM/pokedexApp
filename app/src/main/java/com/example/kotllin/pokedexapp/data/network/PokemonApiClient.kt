package com.example.kotllin.pokedexapp.data.network

import com.example.kotllin.pokedexapp.data.network.model.PokedexObject
import com.example.kotllin.pokedexapp.data.network.model.pokemon.Pokemon

class PokemonApiClient {
    private lateinit var api: PokemonAPIService

    suspend fun getPokemonList(limit:Int): PokedexObject?{
        api = NetworkModuleDI()
        return try{
            api.getPokemonList(limit)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun getPokemonInfo(numberPokemon:Int): Pokemon? {
        api = NetworkModuleDI()
        return try{
            api.getPokemonInfo(numberPokemon)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }
}