// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Team(
    @SerialName("teamId") val teamId: Int? = null, // 1611661330
    @SerialName("teamName") val teamName: String? = null, // Dream
    @SerialName("teamCity") val teamCity: String? = null, // Atlanta
    @SerialName("teamTricode") val teamTricode: String? = null, // ATL
    @SerialName("wins") val wins: Int? = null, // 7
    @SerialName("losses") val losses: Int? = null, // 10
    @SerialName("score") val score: Int? = null, // 75
    @SerialName("seed") val seed: Int? = null, // null
    @SerialName("inBonus") val inBonus: Int? = null, // null
    @SerialName("timeoutsRemaining") val timeoutsRemaining: Int? = null, // 0
    @SerialName("periods") val periods: List<Period>? = null,
)
