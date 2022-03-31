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

package dev.whosnickdoglio.scores.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import com.slack.eithernet.ApiResult
import dev.whosnickdoglio.nba.state.ScoresState
import dev.whosnickdoglio.scores.di.injector
import dev.whosnickdoglio.scores.widget.actions.RefreshAction
import dev.whosnickdoglio.scores.widget.state.ScoresStateDefinition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 *
 */
class ScoresWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = ScoreWidget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        if (context != null) {
            // TODO this is terrible
            CoroutineScope(Dispatchers.Default).launch {
                val ids =
                    GlanceAppWidgetManager(context = context).getGlanceIds(glanceAppWidget::class.java)

                val service = context.injector.service
                updateAppWidgetState(
                    context = context,
                    definition = ScoresStateDefinition,
                    glanceId = ids.first(),
                    updateState = { currentState ->
                        val today = LocalDate.now()

                        val result = service.retrieveGameData(
                            startDate = today,
                            endDate = today
                        )
                        if (result is ApiResult.Success) {
                            return@updateAppWidgetState ScoresState(
                                currentIndex = currentState.currentIndex ?: 0,
                                // TODO figure out how to move games that haven't started yet.
                                games = result.value.games.sortedBy { it.period }
                            )
                        } else {
                            // TODO better error handling
                            return@updateAppWidgetState ScoresState()
                        }
                    }
                )

                glanceAppWidget.update(context, ids.first())
            }
        }

    }
}
