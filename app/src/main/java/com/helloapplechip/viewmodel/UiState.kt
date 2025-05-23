package com.helloapplechip.viewmodel

import com.helloapplechip.model.Character

// UI State sealed class to handle different states
sealed class UiState {
    object Loading : UiState()
    data class Success(val characters: List<Character>) : UiState()
    data class Error(val message: String) : UiState()
}