package com.helloapplechip.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("results")
    val results: List<Character>,
    @SerialName("info")
    val info: Info
)

@Serializable
data class Info(
    @SerialName("count")
    val count: Int,
    @SerialName("pages")
    val pages: Int,
    @SerialName("next")
    val next: String? = null,
    @SerialName("prev")
    val prev: String? = null
)

@Serializable
data class Character(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("type")
    val type: String = "",
    @SerialName("gender")
    val gender: String,
    @SerialName("origin")
    val origin: Location,
    @SerialName("location")
    val location: Location,
    @SerialName("image")
    val image: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("created")
    val created: String
)

@Serializable
data class Location(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)