/*
 * MIT License
 *
 * Copyright (c) 2023 Nicholas Doglio
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

import androidx.work.ListenableWorker
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import dev.whosnickdoglio.anvil.AppScope
import dev.whosnickdoglio.scores.widget.work.UpdateScoresWorker
import dev.whosnickdoglio.workmanager.AssistedWorkerFactory
import dev.whosnickdoglio.workmanager.WorkerKey

@Module
@ContributesTo(AppScope::class)
interface WorkerModule {

    @Multibinds
    fun workerFactories():
        Map<Class<out ListenableWorker>, AssistedWorkerFactory<out ListenableWorker>>

    @WorkerKey(UpdateScoresWorker::class)
    @IntoMap
    @Binds
    fun bindWorkerFactory(
        factory: UpdateScoresWorker.Factory
    ): AssistedWorkerFactory<UpdateScoresWorker>
}
