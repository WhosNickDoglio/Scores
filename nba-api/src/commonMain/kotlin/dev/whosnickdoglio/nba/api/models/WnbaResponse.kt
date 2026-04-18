// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WnbaResponse(
    val meta: Meta? = null,
    val scoreboard: Scoreboard? = null
)
