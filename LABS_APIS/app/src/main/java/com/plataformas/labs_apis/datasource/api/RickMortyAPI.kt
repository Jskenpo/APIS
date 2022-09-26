package com.plataformas.labs_apis.datasource.api

import android.telecom.Call
import com.plataformas.labs_apis.datasource.model.AllCharactersResponse
import com.plataformas.labs_apis.datasource.model.DetailsCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyAPI {
    @GET("/api/character")
    fun getCharacters(): retrofit2.Call<AllCharactersResponse>

    @GET("/api/character/{id}")
    fun getCharacterDetails(@Path("id") id: String): retrofit2.Call<DetailsCharacterResponse>
}