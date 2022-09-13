package com.plataformas.labs_apis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plataformas.labs_apis.adapter.Adapter
import com.plataformas.labs_apis.database.Character
import com.plataformas.labs_apis.database.RickAndMortyDB


class FragmentList() : Fragment(R.layout.fragment_list), Adapter.RecyclerViewCharacterClickHandler {

    private lateinit var recycler: RecyclerView
    private lateinit var list: MutableList<Character>
    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.listofCharacters)
        initRecycler()

    }

    private fun initRecycler() {
        list= RickAndMortyDB.getCharacters()
        adapter = Adapter(list, this)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
    }

    override fun onCharacterClicked(character: Character) {
        val action = FragmentListDirections.actionFragmentListToCharacterDetailsFragment(character)
        requireView().findNavController().navigate(action)
    }


}