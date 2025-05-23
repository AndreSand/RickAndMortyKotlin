package com.helloapplechip.network

import com.helloapplechip.model.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    object NetworkModule {

        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val characterService: CharacterService = retrofit.create(CharacterService::class.java)
    }

    companion object {
        const val ROOT_URL = "https://rickandmortyapi.com/api/"
    }
}
