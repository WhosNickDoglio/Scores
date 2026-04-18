// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.di

import android.content.Context
import androidx.work.WorkerFactory
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class)
interface ScoresAppDependencyGraph {
    val workerFactory: WorkerFactory
}

/**
 * A class that provides and maintains a single instance of a [ScoresAppDependencyGraph].
 *
 * **NOTE**: This should be applied to the Application class.
 */
interface GraphProvider {

    /** An instance of the [ScoresAppDependencyGraph]. */
    val graph: ScoresAppDependencyGraph
}

/**
 * Exposes the [ScoresAppDependencyGraph] via an [android.content.Context] for easy member
 * injection.
 */
val Context.appDependencyGraph
    get() = (applicationContext as GraphProvider).graph
