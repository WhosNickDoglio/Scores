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

package dev.whosnickdoglio.scores.widget

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.nba.api.Result
import dev.whosnickdoglio.scores.ui.MultipleGameList
import dev.whosnickdoglio.scores.ui.SingleGame
import dev.whosnickdoglio.scores.ui.SingleScoreCompact
import dev.whosnickdoglio.scores.widget.actions.NavigateActionCallback
import dev.whosnickdoglio.scores.widget.di.WidgetComponent
import dev.whosnickdoglio.scores.widget.di.create
import kotlinx.collections.immutable.toImmutableList

/** An implementation of [GlanceAppWidget] that shows sports scores. */
class ScoresWidget : GlanceAppWidget() {

    private val component by lazy { WidgetComponent::class.create() }

    override val stateDefinition: GlanceStateDefinition<*> = component.glanceStateDefinition

    override val sizeMode: SizeMode =
        SizeMode.Responsive(setOf(SINGLE_GAME_COMPACT, SINGLE_GAME, MULTI_GAME_LIST))

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val initialState = fetchState()
        provideContent {
            var state by remember { mutableStateOf(initialState) }

            var isRefreshing by remember { mutableStateOf(false) }

            LaunchedEffect(isRefreshing) {
                if (isRefreshing) {
                    state = fetchState()
                    Log.i("ScoresWidget", "REFRESHING")
                    isRefreshing = false
                }
            }

            Scores(state = state, onRefresh = { isRefreshing = true })
        }
    }

    private suspend fun fetchState(): ScoresWidgetState {
        val nbaScoreboardNetworkClient = component.nbaScoreboardNetworkClient
        return when (val data = nbaScoreboardNetworkClient.fetch()) {
            is Result.Success -> {
                ScoresWidgetState(
                    currentIndex = 0,
                    games = data.data.scoreboard?.games.orEmpty(),
                    areThereGamesToday = data.data.scoreboard?.games?.isNotEmpty() == true)
            }

            is Result.Failure -> ScoresWidgetState()
        }
    }
}

@Composable
private fun Scores(
    state: ScoresWidgetState,
    onRefresh: () -> Unit,
    modifier: GlanceModifier =
        GlanceModifier.fillMaxWidth().fillMaxHeight().appWidgetBackground().background(Color.White)
) {
    Column {
        when (LocalSize.current) {
            SINGLE_GAME_COMPACT ->
                SingleScoreCompact(
                    modifier = modifier,
                    onRefresh = onRefresh,
                    onNavigateUp = { NavigateActionCallback.up() },
                    onNavigateDown = { NavigateActionCallback.down() },
                    game =
                        if (state.games.isEmpty()) {
                            null
                        } else {
                            state.games[state.currentIndex ?: 0]
                        })

            MULTI_GAME_LIST ->
                MultipleGameList(
                    modifier = modifier,
                    onRefresh = onRefresh,
                    games = state.games.toImmutableList()
                )

            SINGLE_GAME ->
                SingleGame(
                    modifier = modifier,
                    onRefresh = onRefresh,
                    onNavigateUp = { NavigateActionCallback.up() },
                    onNavigateDown = { NavigateActionCallback.down() },
                    game =
                        if (state.games.isEmpty()) {
                            null
                        } else {
                            state.games[state.currentIndex ?: 0]
                        })
        }
    }
}

// TODO these are really just guesses, come up with better sizing numbers
//  Better naming
private val SINGLE_GAME_COMPACT = DpSize(width = 80.dp, height = 80.dp)
private val SINGLE_GAME = DpSize(width = 125.dp, height = 80.dp)
private val MULTI_GAME_LIST = DpSize(width = 125.dp, height = 150.dp)
