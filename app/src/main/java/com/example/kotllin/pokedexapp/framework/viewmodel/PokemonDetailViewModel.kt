package com.example.kotllin.pokedexapp.framework.viewmodel

import android.os.Debug
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotllin.pokedexapp.data.network.model.pokemon.Pokemon
import com.example.kotllin.pokedexapp.domain.PokemonInfoRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailViewModel: ViewModel() {
    val pokemonDetailObjectLiveData = MutableLiveData<Pokemon?>()
    private val pokemonInfoRequirement = PokemonInfoRequirement()

    fun getPokemonInfo(url: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            var pokemonStringNumber:String = url!!.replace("https://pokeapi.co/api/v2/pokemon/","")
            pokemonStringNumber = pokemonStringNumber.replace("/","")
            val pokemonNumber:Int = Integer.parseInt(pokemonStringNumber)

            val result: Pokemon? = pokemonInfoRequirement(pokemonNumber)
            CoroutineScope(Dispatchers.Main).launch {
                pokemonDetailObjectLiveData.postValue(result)
            }
        }
    }
}