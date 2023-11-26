package com.flexcode.freetogame.data.repository

import com.flexcode.freetogame.data.FreeToGameApiService
import com.flexcode.freetogame.data.dto.FreeToGameDescriptionDto
import com.flexcode.freetogame.data.dto.FreeToGameDto
import com.flexcode.freetogame.data.mappers.toDomain
import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.domain.models.FreeToGameDescription
import com.flexcode.freetogame.domain.repository.FreeToGameRepository
import com.flexcode.freetogame.utils.NetworkResult
import com.flexcode.freetogame.utils.safeApiCall
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implements the  [FreeToGameRepository] and manages interaction with the [FreeToGameApiService]
 * @property freeToGameApiService api service to connect to the server
 */
class FreeToGameRepositoryImpl(
    private val freeToGameApiService: FreeToGameApiService
) : FreeToGameRepository {

    /**
     * Manages getting all games and updates the [Flow] accordingly
     *
     */
    override suspend fun getAllFreeGames(): Flow<NetworkResult<List<FreeGame>>> = flow {
        val response = safeApiCall {
            freeToGameApiService.getAllFreeGames().map { it.toDomain() }
        }

        emit(response)

    }

    /**
     * Manages filtering  games by platform  and updates the [Flow] accordingly
     *@param platform
     */
    override suspend fun getGamesByPlatform(platform: String): Flow<NetworkResult<List<FreeGame>>> =
        flow {
            val response = safeApiCall {
                freeToGameApiService.getGamesByPlatform(platform).map { it.toDomain() }
            }

            emit(response)
        }

    /**
     * Manages filtering  games by platform and category  and updates the [Flow] accordingly
     *@param platform
     *@param category
     */
    override suspend fun getGamesByCategoryPlatform(
        platform: String,
        category: String
    ): Flow<NetworkResult<List<FreeGame>>> = flow {
        flow {
            val response = safeApiCall {
                freeToGameApiService.getGamesByCategoryPlatform(
                    platform = platform,
                    category = category
                ).body<List<FreeToGameDto>>().map { it.toDomain() }
            }

            emit(response)
        }
    }

    /**
     * Manages getting a game details and updates the [Flow] accordingly
     * @param gameId
     */
    override suspend fun getGameDetails(gameId: Int): Flow<NetworkResult<FreeToGameDescription>> =
        flow {
            val response = safeApiCall {
                freeToGameApiService.getGameDetails(gameId).body<FreeToGameDescriptionDto>().toDomain()
            }
            emit(response)
        }
}