package com.flexcode.freetogame.di

import com.flexcode.freetogame.data.FreeToGameApiService
import com.flexcode.freetogame.data.repository.FreeToGameRepositoryImpl
import com.flexcode.freetogame.domain.repository.FreeToGameRepository
import com.flexcode.freetogame.presentation.viewmodels.FreeToGameViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val BASE_URL = "www.freetogame.com"

fun commonModule() = module {
    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = BASE_URL
                    protocol = URLProtocol.HTTPS
                }
            }



            install(Logging) {
                logger = object :Logger {
                    override fun log(message: String) {
                        println("HTTP call$message" )
                    }
                }
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    },
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 20000L
                connectTimeoutMillis = 20000L
                socketTimeoutMillis = 20000L
            }
        }
    }

    single { FreeToGameApiService(httpClient = get()) }

    single<FreeToGameRepository> { FreeToGameRepositoryImpl(freeToGameApiService = get()) }

    single {
        FreeToGameViewModel(freeToGameRepository = get())
    }
}