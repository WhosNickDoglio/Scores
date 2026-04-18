// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api

sealed interface LeagueId {
    val id: String

    data object Wnba : LeagueId {
        override val id: String = "10"
    }

    data object Nba : LeagueId {
        override val id: String = "00"
    }
}
