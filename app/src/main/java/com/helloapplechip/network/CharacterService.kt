package com.helloapplechip.network

import android.util.Log
import com.helloapplechip.model.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CharacterService {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("KtorClient", message)
                }
            }
            level = LogLevel.INFO
        }
    }

    suspend fun getCharacters(): CharacterResponse {
        try {
            val response = client.get("${ROOT_URL}character")
            
            if (response.status.isSuccess()) {
                return response.body<CharacterResponse>()
            } else {
                throw Exception("HTTP ${response.status.value}: ${response.status.description}")
            }
        } catch (e: Exception) {
            Log.e("CharacterService", "Error fetching characters", e)
            throw e
        }
    }

    fun close() {
        client.close()
    }

    companion object {
        const val ROOT_URL = "https://rickandmortyapi.com/api/"
    }
}
