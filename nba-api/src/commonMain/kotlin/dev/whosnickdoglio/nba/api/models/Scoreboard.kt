// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models

import kotlinx.serialization.Serializable

@Serializable
public data class Scoreboard(
    val gameDate: String? = null, // 2024-06-30
    val leagueId: String? = null, // 10
    val leagueName: String? = null, // Women's National Basketball Association
    val games: List<Game>? = null,
)
