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

package dev.whosnickdoglio.scores

import android.app.Application
import androidx.work.Configuration
import dev.whosnickdoglio.scores.di.AppComponent
import dev.whosnickdoglio.scores.di.ComponentProvider
import dev.whosnickdoglio.scores.di.create

/**
 * Our Android [Application] class that acts as our [ComponentProvider] to maintain a single
 * instance of our [AppComponent] as well as initializing some debug tools.
 */
class ScoresApplication : Application(), ComponentProvider, Configuration.Provider {

    override val component: AppComponent by lazy { AppComponent::class.create() }

    override val workManagerConfiguration: Configuration =
        Configuration.Builder().setWorkerFactory(component.workerFactory).build()
}
