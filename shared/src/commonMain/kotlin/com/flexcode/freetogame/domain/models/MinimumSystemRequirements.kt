package com.flexcode.freetogame.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinimumSystemRequirements(
    @SerialName("graphics")
    val graphics: String,
    @SerialName("memory")
    val memory: String,
    @SerialName("os")
    val os: String,
    @SerialName("processor")
    val processor: String,
    @SerialName("storage")
    val storage: String
)