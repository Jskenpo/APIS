package com.plataformas.labs_apis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.plataformas.labs_apis.adapter.Adapter
import com.plataformas.labs_apis.database.Character
import com.plataformas.labs_apis.database.RickAndMortyDB
import com.plataformas.labs_apis.datasource.api.RetrofitInstance
import com.plataformas.labs_apis.datasource.model.AllCharactersResponse
import com.plataformas.labs_apis.datasource.model.DetailsCharacterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentList : Fragment(R.layout.fragment_list), Adapter.RecyclerViewCharacterClickHandler {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var personajes: MutableList<DetailsCharacterResponse>
    private lateinit var toolbar: MaterialToolbar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.listofCharacters)
        toolbar = view.findViewById(R.id.toolbar_characterList)
        getCharacters()
        setListeners()
        setToolbar()
    }

    private fun setToolbar(){
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(setOf(R.id.fragmentList))
        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun setListeners(){
        toolbar.setOnMenuItemClickListener {
            menuItem->
            when(menuItem.itemId){
                R.id.ordenar_az -> {
                    personajes.sortBy { it.name }
                    adapter.notifyDataSetChanged()
                    true
                }
                R.id.ordenar_za -> {
                    personajes.sortByDescending { it.name }
                    adapter.notifyDataSetChanged()
                    true
                }
                R.id.cerrarSesion -> {
                    cerrarSesion()
                    true
                }
                else -> true

            }
        }
    }

    private fun initRecycler(personajes: MutableList<DetailsCharacterResponse>) {
        this.personajes = personajes

        adapter = Adapter(this.personajes, this)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter
    }

    private fun getCharacters() {
        RetrofitInstance.api.getCharacters().enqueue(object: Callback<AllCharactersResponse> {
            override fun onResponse(
                call: Call<AllCharactersResponse>,
                response: Response<AllCharactersResponse>
            ) {
                if (response.isSuccessful) {
                    val res = response.body()?.results
                    initRecycler(res ?: mutableListOf())
                }
            }

            override fun onFailure(call: Call<AllCharactersResponse>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.mensaje_error), Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onItemClicked(character: DetailsCharacterResponse) {
        val action = FragmentListDirections.actionFragmentListToCharacterDetailsFragment(character.id)
        view?.findNavController()?.navigate(action)
    }

    private fun cerrarSesion(){
        CoroutineScope(Dispatchers.IO).launch{
            requireContext().dataStore.removePreferencesValue(KEY_EMAIL)
            CoroutineScope(Dispatchers.Main).launch {
                requireView().findNavController().navigate(FragmentListDirections.actionFragmentListToLoginFragment())
            }
        }

    }


}


