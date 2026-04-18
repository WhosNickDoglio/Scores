// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val gameId: String? = null, // 1022400106
    val gameCode: String? = null, // 20240630/ATLNYL
    val gameStatus: Int? = null, // 3
    val gameStatusText: String? = null, // Final
    val period: Int? = null, // 4
    val gameClock: String? = null,
    val gameTimeUTC: String? = null, // 2024-06-30T17:00:00Z
    val gameEt: String? = null, // 2024-06-30T13:00:00Z
    val regulationPeriods: Int? = null, // 4
    val ifNecessary: Boolean? = null, // false
    val seriesGameNumber: String? = null,
    val gameLabel: String? = null, // Regular Season
    val gameSubLabel: String? = null,
    val seriesText: String? = null,
    val seriesConference: String? = null,
    val poRoundDesc: String? = null,
    val gameSubtype: String? = null,
    val homeTeam: Team? = null,
    val awayTeam: Team? = null,
)
