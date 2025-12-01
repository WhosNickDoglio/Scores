// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api

public sealed interface LeagueId {
    public val id: String

    public data object Wnba : LeagueId {
        override val id: String = "10"
    }

    public data object Nba : LeagueId {
        override val id: String = "00"
    }
}
