// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models

import kotlinx.serialization.Serializable

@Serializable
public data class WnbaResponse(val meta: Meta? = null, val scoreboard: Scoreboard? = null)
