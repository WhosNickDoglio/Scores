// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget.di

import dev.whosnickdoglio.inject.WidgetScope
import dev.whosnickdoglio.nba.api.NbaScoreboardNetworkClient
import dev.whosnickdoglio.scores.widget.ScoresStateDefinition
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.SingleIn

@SingleIn(WidgetScope::class)
@DependencyGraph(WidgetScope::class)
interface WidgetDependencyGraph {
    val glanceStateDefinition: ScoresStateDefinition
    val nbaScoreboardNetworkClient: NbaScoreboardNetworkClient
}
