package com.helloapplechip.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloapplechip.model.Character
import com.helloapplechip.network.CharacterService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val characterService = CharacterService.NetworkModule.characterService

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Keep the original characters flow for backward compatibility
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = characterService.getCharacters()
                _characters.value = response.results
                _uiState.value = UiState.Success(response.results)
                Log.d("CharacterViewModel", "Successfully fetched ${response.results.size} characters")
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error occurred"
                _uiState.value = UiState.Error(errorMessage)
                Log.e("CharacterViewModel", "Error fetching characters: $errorMessage", e)
            }
        }
    }

    fun retry() {
        fetchCharacters()
    }
}