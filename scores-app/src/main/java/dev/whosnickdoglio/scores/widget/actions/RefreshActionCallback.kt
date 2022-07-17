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

package dev.whosnickdoglio.scores.widget.actions

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import com.slack.eithernet.ApiResult
import dev.whosnickdoglio.nba.models.Game
import dev.whosnickdoglio.scores.di.injector
import dev.whosnickdoglio.scores.widget.ScoresWidget
import dev.whosnickdoglio.scores.widget.state.ScoresStateDefinition
import dev.whosnickdoglio.widget.state.ScoresWidgetState
import java.time.LocalDate

class RefreshActionCallback : ActionCallback {

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val service = context.injector.service
        val logger = context.injector.logger
        updateAppWidgetState(
            context = context,
            definition = ScoresStateDefinition,
            glanceId = glanceId
        ) { currentState ->
            val today = LocalDate.now()

            val result = service.retrieveGameData(
                startDate = today,
                endDate = today
            )
            if (result is ApiResult.Success) {
                logger.log("Updating widget state. glanceId: $glanceId")

                return@updateAppWidgetState ScoresWidgetState(
                    currentIndex = currentState.calculateNewIndex(),
                    games = result.value.games.orEmpty().sortedGames(),
                    areThereGamesToday = result.value.games?.isNotEmpty() == true
                )
            } else {
                // TODO better error handling
                return@updateAppWidgetState ScoresWidgetState()
            }
        }

        ScoresWidget().update(context, glanceId)
    }

    // TODO figure out how to move games that haven't started yet.
    private fun List<Game>.sortedGames(): List<Game> {
        val inProgressGames = this.filter { it.period != 0 }.sortedBy { it.period }
        val notStartedGames = this.filter { it.period == 0 }

        return buildList {
            addAll(inProgressGames)
            addAll(notStartedGames)
        }
    }

    private fun ScoresWidgetState.calculateNewIndex(): Int {
        val index = currentIndex ?: 0

        return if (index > games.lastIndex) 0 else index
    }
}
