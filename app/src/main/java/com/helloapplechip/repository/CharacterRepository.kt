package com.helloapplechip.repository

import com.helloapplechip.model.Character
import com.helloapplechip.network.CharacterService

class CharacterRepository {
    
    private val characterService = CharacterService()

    suspend fun getCharacters(): Result<List<Character>> {
        return try {
            val response = characterService.getCharacters()
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun close() {
        characterService.close()
    }
}
