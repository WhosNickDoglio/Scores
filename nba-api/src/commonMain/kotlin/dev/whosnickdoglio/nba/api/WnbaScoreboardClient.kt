// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api

import dev.whosnickdoglio.inject.WidgetScope
import dev.whosnickdoglio.nba.api.models.WnbaResponse
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

// https://nba-prod-us-east-1-mediaops-stats.s3.amazonaws.com/WNBA/liveData/scoreboard/todaysScoreboard_10.json

// https://cdn.nba.com/static/json/liveData/scoreboard/todaysScoreboard_00.json
@Inject
@ContributesBinding(WidgetScope::class)
@ContributesBinding(AppScope::class)
@Suppress("TooGenericExceptionCaught")
public class WnbaScoreboardClient(private val client: HttpClient) : NbaScoreboardNetworkClient {
    override suspend fun fetch(): Result<WnbaResponse> =
        try {
            val data = client.get("$BASE_URL/liveData/scoreboard/todaysScoreboard_10.json")
            Result.Success(data.body())
        } catch (e: Exception) {
            Result.Failure(e.message.orEmpty())
        }
}

private const val BASE_URL = "https://cdn.nba.com/static/json/"
