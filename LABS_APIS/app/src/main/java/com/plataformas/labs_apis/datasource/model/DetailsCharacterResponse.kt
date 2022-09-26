package com.plataformas.labs_apis.datasource.model

data class DetailsCharacterResponse(
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String
)