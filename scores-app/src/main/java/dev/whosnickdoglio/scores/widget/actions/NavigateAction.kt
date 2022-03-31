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
import androidx.glance.action.actionParametersOf
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import dev.whosnickdoglio.nba.state.ScoresState
import dev.whosnickdoglio.scores.widget.ScoreWidget
import dev.whosnickdoglio.scores.widget.state.ScoresStateDefinition

class NavigateAction : ActionCallback {

    private enum class Direction { UP, DOWN }

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val direction: Direction? = parameters[navKey]

        updateAppWidgetState(
            context = context,
            definition = ScoresStateDefinition,
            glanceId = glanceId,
            updateState = { currentState ->
                val currentIndex = currentState.currentIndex ?: 0

                // TODO more robust logic and handling of this
                val newIndex = when (direction) {
                    Direction.DOWN -> if (currentIndex == currentState.games.lastIndex) 0 else currentIndex + 1
                    Direction.UP -> if (currentIndex == 0) 0 else currentIndex - 1
                    null -> currentState.currentIndex
                }

                return@updateAppWidgetState ScoresState(newIndex, currentState.games)
            }
        )

        ScoreWidget().update(context, glanceId)
    }

    companion object {
        private val navKey = ActionParameters.Key<Direction>("direction")

        fun up() =
            actionRunCallback<NavigateAction>(parameters = actionParametersOf(navKey to Direction.UP))

        fun down() =
            actionRunCallback<NavigateAction>(parameters = actionParametersOf(navKey to Direction.DOWN))
    }
}

