package com.helloapplechip.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloapplechip.model.Character
import com.helloapplechip.network.CharacterService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val characterService = CharacterService.NetworkModule.characterService

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    init {
        fetchPictures()
    }

    private fun fetchPictures() {
        viewModelScope.launch {
            try {
                val response = characterService.getCharacters()
                _characters.value = response.results
                Log.d("CharacterViewModel", "Error fetching characters: ${response}")
            } catch (e: Exception) {
                Log.d("CharacterViewModel", "Error fetching characters: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
