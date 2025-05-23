package com.helloapplechip.network

import com.helloapplechip.model.CharacterResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    object NetworkModule {

        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }

        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()

        val characterService: CharacterService = retrofit.create(CharacterService::class.java)
    }

    companion object {
        const val ROOT_URL = "https://rickandmortyapi.com/api/"
    }
}