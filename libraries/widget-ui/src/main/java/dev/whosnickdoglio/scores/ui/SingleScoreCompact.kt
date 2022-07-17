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

package dev.whosnickdoglio.scores.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentHeight
import androidx.glance.layout.wrapContentWidth
import androidx.glance.text.Text
import dev.whosnickdoglio.nba.models.Game

// TODO clean this up
//  Centering seems off with Team columns and navigation row icons
/**
 * This is the compact version of [SingleGame] which just shows the team abbreviations instead of the
 * full name (NYK instead of New York Knicks).
 *
 * @param modifier
 * @param onRefresh
 * @param onNavigateUp
 * @param onNavigateDown
 * @param game
 */
@Composable
fun SingleScoreCompact(
    modifier: GlanceModifier = GlanceModifier,
    onRefresh: Action,
    onNavigateUp: Action,
    onNavigateDown: Action,
    game: Game? = null,
//    forceRefresh: Boolean = false,
) {
    Column(
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        Row(
            modifier = GlanceModifier.fillMaxWidth().wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val chipContentHeight = GlanceModifier.fillMaxHeight()
            if (game != null) {
                TeamColumn(
                    modifier = chipContentHeight,
                    name = game.homeTeam.abbreviation,
                    score = game.homeTeamScore.toString()
                )
                Spacer(modifier = GlanceModifier.width(10.dp))
                TeamColumn(
                    modifier = chipContentHeight,
                    name = game.visitorTeam.abbreviation,
                    score = game.visitorTeamScore.toString()
                )
            } else {
                EmptyChip(
                    modifier = GlanceModifier.fillMaxWidth().fillMaxSize(),
                    onRefresh = onRefresh
                )
            }
        }

        if (game != null) {
            Text(
                text = game.status,
                style = ScoresWidgetTheme.textStyle
            ) // More info on game
            Spacer(GlanceModifier.height(2.dp))
            NavigationRow(
                modifier = GlanceModifier.height(50.dp).fillMaxWidth(),
                onRefresh = onRefresh,
                onNavigateUp = onNavigateUp,
                onNavigateDown = onNavigateDown,
            )
        }
    }
}

//@Composable
//private fun Loading(
//    modifier: GlanceModifier = GlanceModifier,
//    action: Action
//) {
//    Text(text = "Loading...")
//}

@Composable
private fun NavigationRow(
    modifier: GlanceModifier = GlanceModifier,
    onRefresh: Action,
    onNavigateUp: Action,
    onNavigateDown: Action,
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            provider = ImageProvider(R.drawable.back),
            contentDescription = stringResource(R.string.cd_navigation_previous),
            modifier = GlanceModifier.clickable(onNavigateUp)
        )
        Refresh(onRefresh = onRefresh)
        Image(
            provider = ImageProvider(R.drawable.next),
            contentDescription = stringResource(R.string.cd_navigation_next),
            modifier = GlanceModifier.clickable(onNavigateDown)
        )
    }
}


@Composable
private fun EmptyChip(
    modifier: GlanceModifier = GlanceModifier,
    onRefresh: Action,
) {
    Column(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.no_games), style = ScoresWidgetTheme.textStyle)
        Refresh(onRefresh = onRefresh)
    }
}

@Composable
private fun TeamColumn(
    modifier: GlanceModifier = GlanceModifier,
    name: String,
    score: String
) {
    Column(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = GlanceModifier.height(2.dp))
        Text(text = name, style = ScoresWidgetTheme.textStyle)
        Spacer(modifier = GlanceModifier.height(2.dp))
        if (score.isNotEmpty()) {
            Text(text = score, style = ScoresWidgetTheme.textStyle)
            Spacer(modifier = GlanceModifier.height(2.dp))
        }
    }
}
