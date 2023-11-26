package com.flexcode.freetogame.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.flexcode.freetogame.domain.repository.FreeToGameRepository
import com.flexcode.freetogame.presentation.state.FreeToGameEvent
import com.flexcode.freetogame.presentation.state.GamesState
import com.flexcode.freetogame.utils.NetworkResult
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * shared  ViewModel class for the apps
 */
class FreeToGameViewModel(
    private val freeToGameRepository: FreeToGameRepository
) : StateScreenModel<GamesState>(GamesState()) {

    val platforms = mutableListOf("All", "Pc", "Browser")
    val categories = mutableListOf(
        "mmorpg", "shooter", "strategy", "moba", "racing", "sports", "social", "sandbox",
        "open-world", "survival", "pvp", "pve", "pixel",
        "voxel", "zombie", "turn-based", "first-person", "third-Person", "top-down", "tank",
        "space", "sailing", "side-scroller", "superhero", "permadeath", "card",
        "battle-royale", "mmo", "mmofps", "mmotps", "3d", "2d", "anime", "fantasy",
        "sci-fi", "fighting", "action-rpg", "action", "military", "martial-arts",
        "flight", "low-spec", "tower-defense", "horror", "mmorts"
    )

    private val _selectedPlatform = mutableStateOf(platforms[0])
    val selectedPlatform: State<String> = _selectedPlatform

    private val _selectedCategory = mutableStateOf("")
    val selectedCategory: State<String> = _selectedCategory

    /**
     * set the selected platform
     */
    fun setPlatform(value: String) {
        _selectedPlatform.value = value
        getAllGames()
    }

    /**
     * set selected category
     */
    fun setCategory(value: String) {
        _selectedCategory.value = value
        filterGamesByCategory(selectedPlatform.value, selectedCategory.value)
    }

    private fun filterGamesByCategory(platform: String, category: String) {

        screenModelScope.launch {
            mutableState.update {
                it.copy(isLoading = true)
            }


            freeToGameRepository.getGamesByCategoryPlatform(platform, category)
                .collect { response ->

                    when (response) {
                        is NetworkResult.Success -> {

                            mutableState.update {
                                it.copy(
                                    allGames = response.data ?: emptyList(), isLoading = false,
                                )
                            }

                        }

                        is NetworkResult.Error -> {

                            mutableState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "${response.errorMessage} ${response.errorCode}"
                                )
                            }
                        }


                    }
                }
        }
    }

    /**
     * called on app launch to get games by all categories on launch
     */
    init {
        getAllGames()
    }

    /**
     * function to get all the free games
     */
    private fun getAllGames() {

        screenModelScope.launch {
            mutableState.update {
                it.copy(isLoading = true)
            }


            freeToGameRepository.getGamesByPlatform(selectedPlatform.value.lowercase())
                .collect { response ->

                    when (response) {
                        is NetworkResult.Success -> {

                            mutableState.update {
                                it.copy(
                                    allGames = response.data ?: emptyList(), isLoading = false,
                                )
                            }

                        }

                        is NetworkResult.Error -> {
                            mutableState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "${response.errorMessage} ${response.errorCode}"
                                )
                            }
                        }


                    }
                }
        }
    }

    /**
     * function to get game details
     */
    fun getGameDetails(id: Int) {

        screenModelScope.launch {
            mutableState.update {
                it.copy(isLoading = true)
            }


            freeToGameRepository.getGameDetails(id)
                .collect { response ->

                    when (response) {
                        is NetworkResult.Success -> {

                            mutableState.update {
                                it.copy(
                                    gameDetails = response.data, isLoading = false,
                                )
                            }

                        }

                        is NetworkResult.Error -> {
                            mutableState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "${response.errorMessage} ${response.errorCode}"
                                )
                            }
                        }


                    }
                }
        }
    }

    /**
     * Event to control screen interactions, clicks , actions, etc.
     */
    fun onEvent(event: FreeToGameEvent) {
        when (event) {
            is FreeToGameEvent.OnCategoryClick -> {

            }

            is FreeToGameEvent.OnPlatFormClick -> {
                setPlatform(event.value)
            }

            is FreeToGameEvent.OnNavigateToDetails -> {

            }

            else -> {}
        }
    }
}