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

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.nba.state.ScoresState
import dev.whosnickdoglio.scores.widget.actions.NavigateAction
import dev.whosnickdoglio.scores.widget.actions.RefreshAction
import dev.whosnickdoglio.scores.widget.state.ScoresStateDefinition
import dev.whosnickdoglio.scores.ui.ScoresWide

/**
 *
 */
class ScoreWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = ScoresStateDefinition

    // TODO make this responsive
    override val sizeMode: SizeMode = SizeMode.Single

    @Composable
    override fun Content() {
        val state = currentState<ScoresState>()

        ScoresWide(
            onRefresh = actionRunCallback<RefreshAction>(),
            onNavigateUp = NavigateAction.up(),
            onNavigateDown = NavigateAction.down(),
            game = if (state.games.isEmpty()) null else state.games[state.currentIndex ?: 0]
        )
    }

    private companion object {
        private val SMALL = ""
        private val LONG = ""
        private val TALL = ""
    }
}
