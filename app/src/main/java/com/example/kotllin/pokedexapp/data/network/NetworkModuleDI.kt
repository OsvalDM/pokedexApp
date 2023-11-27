package com.example.kotllin.pokedexapp.data.network

import com.example.kotllin.pokedexapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {
    private val gsonFactory:GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient()

    operator fun invoke(): PokemonAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(com.example.kotllin.pokedexapp.data.network.NetworkModuleDI.okHttpClient)
            .addConverterFactory(com.example.kotllin.pokedexapp.data.network.NetworkModuleDI.gsonFactory)
            .build()
            .create(PokemonAPIService::class.java)
    }
}