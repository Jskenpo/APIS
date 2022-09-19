package com.plataformas.labs_apis.datasource.api

import retrofit2.http.GET

interface RickMortyAPI {
    @GET("/character")
    fun getCharacters()

}