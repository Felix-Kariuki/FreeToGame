package com.flexcode.freetogame.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinimumSystemRequirementsDto(
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