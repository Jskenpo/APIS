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

class Adapter (private val dataSet: MutableList<Character>, private val listener: RecyclerViewCharacterClickHandler) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_place, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    class ViewHolder(private val view: View, private val listener: RecyclerViewCharacterClickHandler) : RecyclerView.ViewHolder(view) {

        private val imageType: ImageView = view.findViewById(R.id.item_image)

        private val textName: TextView = view.findViewById(R.id.item_name)

        private val textStatus: TextView = view.findViewById(R.id.item_status)

        private val textSpecies: TextView = view.findViewById(R.id.item_species)

        private val layoutCharacter: ConstraintLayout = view.findViewById(R.id.item_recycler_place)

        fun setData(character: Character) {
            textName.text = character.name
            textStatus.text = character.status
            textSpecies.text = character.species
            imageData(character.image)

            layoutCharacter.setOnClickListener() {
                listener.onCharacterClicked(character)
            }
        }

        private fun imageData(image: String) {
            imageType.load(image) {
                transformations(CircleCropTransformation())
                error(R.drawable.ic_baseline_error_outline_24)
                diskCachePolicy(CachePolicy.ENABLED)
                memoryCachePolicy(CachePolicy.ENABLED)
            }
        }

    }


    interface RecyclerViewCharacterClickHandler {

        fun onCharacterClicked(character: Character)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}