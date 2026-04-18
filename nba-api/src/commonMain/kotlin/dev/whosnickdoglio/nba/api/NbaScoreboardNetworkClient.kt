// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api

import dev.whosnickdoglio.nba.api.models.WnbaResponse

interface NbaScoreboardNetworkClient {
    suspend fun fetch(): Result<WnbaResponse>
}

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>

    data class Failure(val message: String) : Result<Nothing>
}
