/*
 * MIT License
 *
 * Copyright (c) 2025 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.whosnickdoglio.scores.di

import android.content.Context
import androidx.work.WorkerFactory
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.SingleIn


@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class, isExtendable = true)
interface ScoresAppDependencyGraph {
    val workerFactory: WorkerFactory
}


/**
 * A class that provides and maintains a single instance of a [AppComponent].
 *
 * **NOTE**: This should be applied to the Application class.
 */
interface ComponentProvider {

    /** An instance of the [AppComponent]. */
    val graph: ScoresAppDependencyGraph
}

/** Exposes the [AppComponent] via an [android.content.Context] for easy member injection. */
val Context.injector
    get() = (applicationContext as ComponentProvider).graph
