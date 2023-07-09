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

package dev.whosnickdoglio.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.squareup.anvil.annotations.ContributesBinding
import dagger.MapKey
import dev.whosnickdoglio.anvil.AppScope
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

// https://gist.github.com/Zhuinden/54136120b15fdabd56aa164c20dc934f

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

fun interface AssistedWorkerFactory<T : ListenableWorker> {
    fun createWorker(appContext: Context, workerParams: WorkerParameters): T
}

@ContributesBinding(AppScope::class)
class ScoresWorkerFactory
@Inject
constructor(private val assistedWorkerFactories: workerFactoryMap) : WorkerFactory() {
    @Suppress("ReturnCount")
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val clazz =
            Class.forName(workerClassName) ?: return null // handle rename or deletion of worker

        val factory =
            assistedWorkerFactories[clazz]
                ?: assistedWorkerFactories.entries
                    .firstOrNull { clazz.isAssignableFrom(it.key) }
                    ?.value
                    ?: return null // delegate to default if not found

        return factory.get().createWorker(appContext, workerParameters)
    }
}

private typealias workerFactoryMap =
    Map<
        Class<out ListenableWorker>,
        @JvmSuppressWildcards
        Provider<AssistedWorkerFactory<out ListenableWorker>>
    >
