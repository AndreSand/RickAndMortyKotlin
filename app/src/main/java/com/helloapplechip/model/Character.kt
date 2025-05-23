package com.helloapplechip.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val image: String
)