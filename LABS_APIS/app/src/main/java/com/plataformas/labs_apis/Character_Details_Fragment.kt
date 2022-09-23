package com.plataformas.labs_apis

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.plataformas.labs_apis.datasource.api.RetrofitInstance
import com.plataformas.labs_apis.datasource.model.DetailsCharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Character_Details_Fragment : Fragment(R.layout.fragment_character__details_) {


    private lateinit var imagen : ImageView
    private lateinit var nombre : TextView
    private lateinit var estado : TextView
    private lateinit var especie : TextView
    private lateinit var genero : TextView
    private lateinit var origen : TextView
    private lateinit var episodios : TextView
    private val args: Character_Details_FragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagen = view.findViewById(R.id.image_details)
        nombre = view.findViewById(R.id.text_details_name)
        estado = view.findViewById(R.id.text_details_status)
        especie = view.findViewById(R.id.text_details_species)
        genero = view.findViewById(R.id.text_details_gender)
        origen = view.findViewById(R.id.text_details_origin)
        episodios = view.findViewById(R.id.text_details_episodes)

        getCharacterDetails()
    }


    private fun getCharacterDetails() {
        RetrofitInstance.api.getCharacterDetails(args.id).enqueue(object : Callback<DetailsCharacterResponse> {
            override fun onResponse(call: Call<DetailsCharacterResponse>, response: Response<DetailsCharacterResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    dataCharacters(response.body()!!)
                }
            }

            override fun onFailure(call: Call<DetailsCharacterResponse>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.mensaje_error), Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun dataCharacters(character: DetailsCharacterResponse) {
        nombre.text = "Nombre: "+character.name
        estado.text = "Estado: "+character.status
        especie.text = "Especie: "+character.species
        genero.text = "Género: "+character.gender
        origen.text = "Origen: "+character.origin
        episodios.text = "Aparece en "+character.episode.size.toString()+" episodios"
        imagen.load(character.image){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_download_24)
            transformations(CircleCropTransformation())
            memoryCachePolicy(CachePolicy.ENABLED)
            error(R.drawable.ic_baseline_error_outline_24)
        }

    }


}
