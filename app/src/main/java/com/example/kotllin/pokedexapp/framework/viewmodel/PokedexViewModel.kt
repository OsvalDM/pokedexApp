package com.example.kotllin.pokedexapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotllin.pokedexapp.data.network.model.PokedexObject
import com.example.kotllin.pokedexapp.domain.PokemonListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.kotllin.pokedexapp.utils.Constants

class PokedexViewModel: ViewModel() {
    val pokedexObjectLiveData = MutableLiveData<PokedexObject?>()
    private val pokemonListRequirement = PokemonListRequirement()

    fun getPokemonList(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: PokedexObject? = pokemonListRequirement(Constants.MAX_POKEMON_NUMBER)
            Log.d("Salida", result?.count.toString())
            CoroutineScope(Dispatchers.Main).launch {
                pokedexObjectLiveData.postValue(result)
            }
        }}
}