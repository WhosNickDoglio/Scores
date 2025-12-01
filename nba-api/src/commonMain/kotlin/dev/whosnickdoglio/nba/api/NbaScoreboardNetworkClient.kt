// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api

import dev.whosnickdoglio.nba.api.models.WnbaResponse

public interface NbaScoreboardNetworkClient {
    public suspend fun fetch(): Result<WnbaResponse>
}

public sealed interface Result<out T> {
    public data class Success<T>(val data: T) : Result<T>

    public data class Failure(val message: String) : Result<Nothing>
}
