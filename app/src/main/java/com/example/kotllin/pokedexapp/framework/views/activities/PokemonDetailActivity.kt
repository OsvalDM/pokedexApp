package com.example.kotllin.pokedexapp.framework.views.activities

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotllin.pokedexapp.R
import com.example.kotllin.pokedexapp.data.network.model.pokemon.Pokemon
import com.example.kotllin.pokedexapp.databinding.ActivityPokemonDetailBinding
import com.example.kotllin.pokedexapp.domain.PokemonInfoRequirement
import com.example.kotllin.pokedexapp.framework.viewmodel.PokemonDetailViewModel
import com.example.kotllin.pokedexapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDetailBinding
    private var pokemonUrl:String? = null
    private lateinit var viewModel: PokemonDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PokemonDetailViewModel::class.java]

        manageIntent()
        initializeBinding()
        initializeObservers()

        viewModel.getPokemonInfo(pokemonUrl)
    }
    private fun initializeBinding(){
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun initializeObservers() {
        viewModel.pokemonDetailObjectLiveData.observe(this) { pokemon ->
            if (pokemon != null) {
                setUpView(pokemon)
            }
        }
    }
    private fun setUpView(pokemon:Pokemon){
        //Regresar
        binding.back.setOnClickListener{
            passViewGoToMain()
        }

        //Fondo
        val imageView = binding.imgPokemon
        val background = binding.fondoPokemonDetail

        val type = pokemon?.types?.get(0)?.type?.name
        setColor(background, type)

        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                val urlImage = pokemon?.sprites?.other?.official_artwork?.front_default.toString()

                val requestOptions = RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .priority(Priority.HIGH)


                Glide.with(this@PokemonDetailActivity).load(urlImage)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }

        //Nombre , id
        binding.nombre.text = pokemon.name
        binding.numero.text = "#" + pokemon.id.toString()

        //Tipos

        if ( pokemon?.types?.get(0)?.type != null ){
            val name = pokemon?.types?.get(0)?.type?.name
            binding.tipoN1.text = name
            setColor(binding.tipo1, name)
        }

        if ( pokemon?.types?.size!! > 1 ){
            val name = pokemon?.types?.get(1)?.type?.name
            binding.tipoN2.text = name
            setColor(binding.tipo2, name)
        }else{
            binding.tipo2.visibility = View.GONE
        }

        //Stats

        for ( stat in pokemon?.stats!! ){
            when( stat.stat.name ){
                "hp" -> {
                    binding.hpNumber.text = stat.base_stat.toString()
                    binding.hpSlider.value = stat.base_stat.toFloat()
                }
                "attack" -> {
                    binding.atkNumber.text = stat.base_stat.toString()
                    binding.atkSlider.value = stat.base_stat.toFloat()
                }
                "defense" -> {
                    binding.defNumber.text = stat.base_stat.toString()
                    binding.defSlider.value = stat.base_stat.toFloat()
                }
                "special-attack" -> {
                    binding.spatkNumber.text = stat.base_stat.toString()
                    binding.spatkSlider.value = stat.base_stat.toFloat()
                }
                "special-defense" -> {
                    binding.spdefNumber.text = stat.base_stat.toString()
                    binding.spdefSlider.value = stat.base_stat.toFloat()
                }
                "speed" -> {
                    binding.spdNumber.text = stat.base_stat.toString()
                    binding.spdSlider.value = stat.base_stat.toFloat()
                }
            }
        }

    }

    private fun manageIntent(){
        if(intent != null){
            pokemonUrl = intent.getStringExtra(Constants.URL_POKEMON)
            Log.d("Salida",pokemonUrl.toString())
        }
    }

    private fun setColor(layout: RelativeLayout, tipo:String?){
        val colorResId = when (tipo) {
            "normal"-> R.color.normal
            "fighting"-> R.color.fighting
            "flying"-> R.color.flying
            "poison"-> R.color.poison
            "ground"-> R.color.ground
            "rock"-> R.color.rock
            "bug"-> R.color.bug
            "ghost"-> R.color.ghost
            "steel"-> R.color.steel
            "fire"-> R.color.fire
            "water"-> R.color.water
            "grass"-> R.color.grass
            "electric"-> R.color.electric
            "psychic"-> R.color.psychic
            "ice"-> R.color.ice
            "dragon"-> R.color.dragon
            "dark"-> R.color.dark
            "fairy"-> R.color.fairy
            else -> R.color.normal
        }

        val colorTrackActive = ContextCompat.getColor(this, colorResId)

        layout.setBackgroundColor(ContextCompat.getColor(this, colorResId))
        binding.hpSlider.trackActiveTintList = ColorStateList.valueOf(colorTrackActive)
        binding.atkSlider.trackActiveTintList = ColorStateList.valueOf(colorTrackActive)
        binding.defSlider.trackActiveTintList = ColorStateList.valueOf(colorTrackActive)
        binding.spatkSlider.trackActiveTintList = ColorStateList.valueOf(colorTrackActive)
        binding.spdefSlider.trackActiveTintList = ColorStateList.valueOf(colorTrackActive)
        binding.spdSlider.trackActiveTintList = ColorStateList.valueOf(colorTrackActive)
    }


    private fun passViewGoToMain() {
        var intent: Intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}