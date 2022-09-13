package com.plataformas.labs_apis

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation

class Character_Details_Fragment : Fragment(R.layout.fragment_character__details_) {


    lateinit var imagen : ImageView
    lateinit var nombre : TextView
    lateinit var estado : TextView
    lateinit var especie : TextView
    lateinit var genero : TextView
    private val args: Character_Details_FragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagen = view.findViewById(R.id.image_details)
        nombre = view.findViewById(R.id.text_details_name)
        estado = view.findViewById(R.id.text_details_status)
        especie = view.findViewById(R.id.text_details_species)
        genero = view.findViewById(R.id.text_details_gender)
    }

    @SuppressLint("SetTextI18n")
    private fun getCharacterDetails() {
        val character = args.character
        getImage(args.character.image)
        nombre.text = "Nombre: "+character.name
        estado.text = "Estado: "+character.status
        especie.text = "Especie: "+character.species
        genero.text = "Género: "+character.gender
    }

    private fun getImage(direc:String) {
        imagen.load(direc){
            transformations(CircleCropTransformation())
            error(R.drawable.ic_baseline_error_outline_24)
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
        }

    }
}
