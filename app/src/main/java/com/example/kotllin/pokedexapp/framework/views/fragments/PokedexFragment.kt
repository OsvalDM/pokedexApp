package com.example.kotllin.pokedexapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotllin.pokedexapp.R
import com.example.kotllin.pokedexapp.data.network.model.PokemonBase
import com.example.kotllin.pokedexapp.databinding.FragmentPokedexBinding
import com.example.kotllin.pokedexapp.framework.adapters.PokemonAdapter
import com.example.kotllin.pokedexapp.framework.viewmodel.PokedexViewModel

class PokedexFragment: Fragment() {
    private var _binding: FragmentPokedexBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PokedexViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter : PokemonAdapter = PokemonAdapter()
    private lateinit var data:ArrayList<PokemonBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PokedexViewModel::class.java]

        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        val root: View = binding.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
        viewModel.getPokemonList()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeComponents(root:View){
        recyclerView = root.findViewById(R.id.RVPokemon)
    }

    private fun initializeObservers() {
        viewModel.pokedexObjectLiveData.observe(viewLifecycleOwner) { poxedexObject ->
            if (poxedexObject != null) {
                setUpRecyclerView(poxedexObject.result)
            }
        }
    }

    private fun setUpRecyclerView(dataForList:ArrayList<PokemonBase>){
        recyclerView.setHasFixedSize(true)
        /*val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)*/
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false    )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PokemonAdapter(dataForList,requireContext())
        recyclerView.adapter = adapter
    }
}