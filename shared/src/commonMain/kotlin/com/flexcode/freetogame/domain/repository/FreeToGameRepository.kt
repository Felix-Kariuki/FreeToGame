package com.flexcode.freetogame.domain.repository

import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.domain.models.FreeToGameDescription
import com.flexcode.freetogame.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * an interface to abstract interaction with the backend service
 */
interface FreeToGameRepository {

    suspend fun getAllFreeGames(): Flow<NetworkResult<List<FreeGame>>>
    suspend fun getGamesByPlatform(platform: String): Flow<NetworkResult<List<FreeGame>>>
    suspend fun getGamesByCategoryPlatform(platform:String,category:String): Flow<NetworkResult<List<FreeGame>>>
    suspend fun getGameDetails(gameId:Int): Flow<NetworkResult<FreeToGameDescription>>

}