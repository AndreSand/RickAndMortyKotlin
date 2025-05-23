package com.helloapplechip.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("results")
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val image: String
)
