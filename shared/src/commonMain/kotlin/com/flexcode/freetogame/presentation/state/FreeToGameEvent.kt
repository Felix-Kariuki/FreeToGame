package com.flexcode.freetogame.presentation.state

import com.flexcode.freetogame.domain.models.FreeGame

sealed interface FreeToGameEvent {

    data class OnPlatFormClick(val value: String) : FreeToGameEvent

    data class OnCategoryClick(val value: String) : FreeToGameEvent

    data class OnNavigateToDetails(val game: FreeGame) : FreeToGameEvent
}