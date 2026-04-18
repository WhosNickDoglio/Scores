// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores

import android.app.Application
import androidx.work.Configuration
import dev.whosnickdoglio.scores.di.GraphProvider
import dev.whosnickdoglio.scores.di.ScoresAppDependencyGraph
import dev.zacsweers.metro.createGraph

/**
 * Our Android [Application] class that acts as our [GraphProvider] to maintain a single instance of
 * our [ScoresAppDependencyGraph] as well as initializing some debug tools.
 */
class ScoresApplication : Application(), GraphProvider, Configuration.Provider {

    override val graph: ScoresAppDependencyGraph by lazy { createGraph() }

    override val workManagerConfiguration: Configuration =
        Configuration.Builder().setWorkerFactory(graph.workerFactory).build()
}
