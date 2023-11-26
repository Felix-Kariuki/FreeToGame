package com.flexcode.freetogame.presentation.state

import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.domain.models.FreeToGameDescription

data class GamesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val allGames:List<FreeGame> = emptyList(),
    val gameDetails: FreeToGameDescription? =null,

)
