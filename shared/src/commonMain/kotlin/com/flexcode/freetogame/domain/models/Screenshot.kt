package com.flexcode.freetogame.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Screenshot(
    @SerialName("image")
    val image: String
)