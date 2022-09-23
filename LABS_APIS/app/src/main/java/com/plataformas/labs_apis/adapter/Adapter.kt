package com.plataformas.labs_apis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.plataformas.labs_apis.R
import com.plataformas.labs_apis.database.Character
import com.plataformas.labs_apis.datasource.model.AllCharactersResponse
import com.plataformas.labs_apis.datasource.model.DetailsCharacterResponse

class Adapter (
    private val dataSet: MutableList<DetailsCharacterResponse>,
    private val listener: RecyclerViewCharacterClickHandler)
    : RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_place, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    class ViewHolder(
        private val view: View,
        private val listener: RecyclerViewCharacterClickHandler)
        : RecyclerView.ViewHolder(view) {
        private val imagen: ImageView = view.findViewById(R.id.item_image)
        private val nombre: TextView = view.findViewById(R.id.item_name)
        private val estado: TextView = view.findViewById(R.id.item_status)
        private val especie: TextView = view.findViewById(R.id.item_species)
        private val layChar: ConstraintLayout = view.findViewById(R.id.item_recycler_place)

        fun setData(character: DetailsCharacterResponse) {
            character.apply {
                imagen.load(character.image) {
                    placeholder(R.drawable.ic_baseline_download_24)
                    transformations(CircleCropTransformation())
                    error(R.drawable.ic_baseline_error_outline_24)
                    memoryCachePolicy(CachePolicy.DISABLED)
                }
                nombre.text = name
                especie.text = species
                estado.text = status
            }
            layChar.setOnClickListener {
                listener.onItemClicked(character)
            }
        }



    }


    interface RecyclerViewCharacterClickHandler {
        fun onItemClicked(character: DetailsCharacterResponse)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}