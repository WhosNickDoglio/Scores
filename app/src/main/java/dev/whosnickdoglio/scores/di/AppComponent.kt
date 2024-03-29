/*
 * MIT License
 *
 * Copyright (c) 2022 Nicholas Doglio
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
import com.squareup.anvil.annotations.MergeComponent
import dev.whosnickdoglio.anvil.AppScope
import dev.whosnickdoglio.scores.dagger.SingleIn
import javax.inject.Singleton

/**
 * A top level [dagger.Component] for our dependency graph, this [dagger.Component] should be a
 * [Singleton] and only initialized once per app launch via the [ComponentProvider]. All
 * [dagger.Modules][dagger.Module] or classes that contribute to [AppScope] will be available in the
 * [AppComponent] graph.
 */
@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {
    val workerFactory: WorkerFactory
}

/**
 * A class that provides and maintains a single instance of a [AppComponent].
 *
 * **NOTE**: This should be applied to the Application class.
 */
interface ComponentProvider {

    /** An instance of the [AppComponent]. */
    val component: AppComponent
}

/** Exposes the [AppComponent] via an [android.content.Context] for easy member injection. */
val Context.injector
    get() = (applicationContext as ComponentProvider).component
