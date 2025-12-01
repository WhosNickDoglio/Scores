// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.MapKey
import kotlin.reflect.KClass

public fun interface AssistedWorkerFactory<T : ListenableWorker> {
    public fun createWorker(appContext: Context, workerParams: WorkerParameters): T
}

@MapKey
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE,
    AnnotationTarget.FUNCTION,
)
public annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@Inject
@ContributesBinding(AppScope::class)
public class ScoresWorkerFactory(
    private val assistedWorkerFactories:
        Map<KClass<out ListenableWorker>, AssistedWorkerFactory<out ListenableWorker>>
) : WorkerFactory() {
    @Suppress("ReturnCount")
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        val factory = assistedWorkerFactories[Class.forName(workerClassName).kotlin]
        return factory?.createWorker(appContext, workerParameters)
    }
}
