// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models

import kotlinx.serialization.Serializable

@Serializable
public data class Meta(
    val version: Int? = null, // 1
    val request: String? =
        null, // https://nba-prod-us-east-1-mediaops-stats.s3.amazonaws.com/WNBA/liveData/scoreboard/todaysScoreboard_10.json
    val time: String? = null, // 2024-06-30 05:38:26.3826
    val code: Int? = null, // 200
)
