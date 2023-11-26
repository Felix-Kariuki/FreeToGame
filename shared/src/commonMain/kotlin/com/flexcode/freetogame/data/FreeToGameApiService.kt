package com.flexcode.freetogame.data

import com.flexcode.freetogame.data.dto.FreeToGameDescriptionDto
import com.flexcode.freetogame.data.dto.FreeToGameDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

/**
 * Api service that connects the Applications to the [FreeToGameApi](https://www.freetogame.com/)
 * backend server
 */
class FreeToGameApiService(
    private val httpClient: HttpClient
) {

    /**
     * Endpoint to get all free games
     * @return List of [FreeToGameDto]
     */

    suspend fun getAllFreeGames(): List<FreeToGameDto>{
        return httpClient.get("/api/games").bodyAsText().let { json->
            Json.decodeFromString(json)
        }
    }

    /**
     * Endpoint to get games by platform
     * @param platform the platform games are being filtered by eg. pc,browser,all
     * @return list of [FreeToGameDto]
     */
    suspend fun getGamesByPlatform(platform:String="all"): List<FreeToGameDto>{
        return httpClient.get("/api/games?platform=$platform").bodyAsText().let { json->
            Json.decodeFromString(json)
        }
    }

    /**
     * Endpoint to get games by platform
     * @param platform the platform games are being filtered by eg. pc,browser,all
     * @param category the game category to filter with eg: mmorpg, shooter, pvp, mmofps and more.
     * see the  [Documentation](https://www.freetogame.com/api-doc)
     * @return list of [FreeToGameDescriptionDto]
     */
    suspend fun getGamesByCategoryPlatform(platform:String="all",category:String="mmorpg"): HttpResponse{
//        return httpClient.get("/api/games?platform=$platform&category=$category&sort-by=release-date").bodyAsText().let { json->
//            Json.decodeFromString(json)
//        }

        return httpClient.get("/api/games") {
            url{
                parameters.append("platform",platform)
                parameters.append("category",category)
                parameters.append("sort-by","release-date")
            }
        }
    }

    /**
     * Endpoint to get the game details
     * @param gameId the id of the game
     * @return [HttpResponse] response containing the JSon [FreeToGameDescriptionDto]
     */

    suspend fun getGameDetails(gameId:Int): HttpResponse {
        return httpClient.get("/api/game") {
            url {
                parameters.append("id", gameId.toString())
            }
        }
    }


}