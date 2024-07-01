/*
 * MIT License
 *
 * Copyright (c) 2024 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.whosnickdoglio.nba.api

import dev.whosnickdoglio.nba.api.models.WnbaResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.tatarka.inject.annotations.Inject

// https://nba-prod-us-east-1-mediaops-stats.s3.amazonaws.com/WNBA/liveData/scoreboard/todaysScoreboard_10.json

// https://cdn.nba.com/static/json/liveData/scoreboard/todaysScoreboard_00.json
@Inject
class WnbaScoreboardClient(private val client: HttpClient) : NbaScoreboardNetworkClient {
    override suspend fun fetch(): Result<WnbaResponse> =
        try {
            val data = client.get("$BASE_URL/liveData/scoreboard/todaysScoreboard_10.json")
            Result.Success(data.body())
        } catch (e: Exception) {
            Result.Failure(e.message.orEmpty())
        }
}

private const val BASE_URL = "https://cdn.nba.com/static/json/"
