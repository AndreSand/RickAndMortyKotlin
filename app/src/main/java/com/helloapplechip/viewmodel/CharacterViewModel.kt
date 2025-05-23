package com.helloapplechip.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloapplechip.model.Character
import com.helloapplechip.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            repository.getCharacters()
                .onSuccess { characters ->
                    _characters.value = characters
                    Log.d("CharacterViewModel", "Successfully fetched ${characters.size} characters")
                }
                .onFailure { exception ->
                    val errorMessage = exception.message ?: "Unknown error occurred"
                    _error.value = errorMessage
                    Log.e("CharacterViewModel", "Error fetching characters: $errorMessage", exception)
                }
            
            _isLoading.value = false
        }
    }

    fun retry() {
        fetchCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        repository.close()
    }
}
