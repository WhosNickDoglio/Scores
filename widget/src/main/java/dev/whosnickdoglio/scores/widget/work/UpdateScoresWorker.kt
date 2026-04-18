// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget.work

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import dev.whosnickdoglio.nba.api.NbaScoreboardNetworkClient
import dev.whosnickdoglio.nba.api.Result as NetworkResult
import dev.whosnickdoglio.scores.widget.ScoresStateDefinition
import dev.whosnickdoglio.scores.widget.ScoresWidget
import dev.whosnickdoglio.scores.widget.ScoresWidgetState
import dev.whosnickdoglio.workmanager.AssistedWorkerFactory
import dev.whosnickdoglio.workmanager.WorkerKey
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.binding

@AssistedInject
class UpdateScoresWorker(
    private val service: NbaScoreboardNetworkClient,
    private val glanceScoresStateDefinition: ScoresStateDefinition,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    @WorkerKey(UpdateScoresWorker::class)
    @ContributesIntoMap(
        AppScope::class,
        binding = binding<AssistedWorkerFactory<out ListenableWorker>>(),
    )
    @AssistedFactory
    fun interface Factory : AssistedWorkerFactory<UpdateScoresWorker>

    override suspend fun doWork(): Result =
        when (val apiResult = service.fetch()) {
            is NetworkResult.Success -> {
                val glanceId =
                    GlanceAppWidgetManager(appContext)
                        .getGlanceIds(ScoresWidget::class.java)
                        .firstOrNull()

                if (glanceId != null) {
                    updateAppWidgetState(
                        context = appContext,
                        definition = glanceScoresStateDefinition,
                        glanceId = glanceId,
                    ) { oldState ->
                        ScoresWidgetState(
                            currentIndex = oldState.currentIndex ?: 0,
                            games =
                                apiResult.data.scoreboard?.games.orEmpty().sortedBy {
                                    it.period ?: 0
                                },
                        )
                    }

                    Result.success()
                } else {
                    Result.failure()
                }
            }

            is NetworkResult.Failure -> Result.failure()
        }
}
