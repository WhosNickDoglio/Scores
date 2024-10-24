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

package dev.whosnickdoglio.scores.widget.work

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.whosnickdoglio.nba.api.NbaScoreboardNetworkClient
import dev.whosnickdoglio.nba.api.Result as NetworkResult
import dev.whosnickdoglio.scores.widget.ScoresStateDefinition
import dev.whosnickdoglio.scores.widget.ScoresWidget
import dev.whosnickdoglio.scores.widget.ScoresWidgetState
import dev.whosnickdoglio.workmanager.AssistedWorkerFactory
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class UpdateScoresWorker(
    private val service: NbaScoreboardNetworkClient,
    private val glanceScoresStateDefinition: ScoresStateDefinition,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

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
                        glanceId = glanceId) { oldState ->
                            ScoresWidgetState(
                                currentIndex = oldState.currentIndex ?: 0,
                                games =
                                    apiResult.data.scoreboard?.games.orEmpty().sortedBy {
                                        it.period ?: 0
                                    })
                        }

                    Result.success()
                } else {
                    Result.failure()
                }
            }

            is NetworkResult.Failure -> Result.failure()
        }

    @Inject
    class Factory(
        private val factory: (context: Context, params: WorkerParameters) -> UpdateScoresWorker
    ) : AssistedWorkerFactory<UpdateScoresWorker> {
        override fun createWorker(
            appContext: Context,
            workerParams: WorkerParameters
        ): UpdateScoresWorker = factory(appContext, workerParams)
    }
}
