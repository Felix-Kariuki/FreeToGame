package com.flexcode.freetogame.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

sealed class NetworkResult<T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(errorCode: Int, errorMessage: String?) :
        NetworkResult<T>(errorCode = errorCode, errorMessage = errorMessage)
}

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(data = apiCall.invoke())

    } catch (e: RedirectResponseException) {

        NetworkResult.Error(
            errorCode = e.response.status.value,
            errorMessage = e.message,
        )
    } catch (e: ClientRequestException) {

        NetworkResult.Error(
            errorCode = e.response.status.value,
            errorMessage = e.message,
        )
    } catch (e: ServerResponseException) {

        NetworkResult.Error(
            errorCode = e.response.status.value,
            errorMessage = e.message,
        )
    } catch (e: Exception) {
        NetworkResult.Error(
            errorCode = 0,
            errorMessage = e.message ?: "An unknown error occurred",
        )
    }
}