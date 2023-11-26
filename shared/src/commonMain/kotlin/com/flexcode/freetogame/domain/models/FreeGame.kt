package com.flexcode.freetogame.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FreeGame(
    @SerialName("developer")
    val developer: String,
    @SerialName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerialName("game_url")
    val gameUrl: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("id")
    val id: Int,
    @SerialName("platform")
    val platform: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String
)