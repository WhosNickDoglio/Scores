// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.di

import dev.whosnickdoglio.inject.WidgetScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@ContributesTo(AppScope::class)
@BindingContainer
public object NbaApiModule {

    @Provides
    public fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Provides
    public fun provideClient(json: Json): HttpClient =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Application.OctetStream)
            }

            // TODO only do in debug builds
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
}

@ContributesTo(WidgetScope::class)
@BindingContainer
public object WidgetNbaApiModule {

    @Provides
    public fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Provides
    public fun provideClient(json: Json): HttpClient =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Application.OctetStream)
            }

            // TODO only do in debug builds
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
}
