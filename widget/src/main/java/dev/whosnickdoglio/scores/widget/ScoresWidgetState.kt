// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget

import dev.whosnickdoglio.nba.api.models.Game
import kotlinx.serialization.Serializable

// TODO move this out of this module
/** A data class that represents all the relevant state information for the Scores widget. */
@Serializable
data class ScoresWidgetState(
    val currentIndex: Int? = null,
    val games: List<Game> = emptyList(),
    val areThereGamesToday: Boolean = true,
    // TODO theming stuff
)
