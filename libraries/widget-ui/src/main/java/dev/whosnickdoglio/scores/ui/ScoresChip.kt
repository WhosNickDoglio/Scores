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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import dev.whosnickdoglio.nba.models.Game

// TODO clean this up
// Centering seems off with Team columns and navigation row icons
@Composable
fun ScoresMini(
    modifier: GlanceModifier = GlanceModifier,
    onRefresh: Action,
    onNavigateUp: Action,
    onNavigateDown: Action,
    game: Game? = null
) {
    Column(
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(100.dp)
            .height(80.dp)
            .background(Color.White)
            .appWidgetBackground()
    ) {
        Row(modifier = GlanceModifier.height(40.dp)) {
            val chipContentHeight = GlanceModifier.height(50.dp)
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
                EmptyChip(onRefresh = onRefresh)
            }
        }

        if (game != null) {
            Text(text = game.status) // More info on game
            Spacer(GlanceModifier.height(2.dp))
            NavigationRow(
                modifier = GlanceModifier.height(20.dp),
                onRefresh = onRefresh,
                onNavigateUp = onNavigateUp,
                onNavigateDown = onNavigateDown,
            )
        }
    }
}

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
            contentDescription = "Navigate to the previous game in the list.",
            modifier = GlanceModifier.clickable(onNavigateUp)
        )
        Refresh(onRefresh = onRefresh)
        Image(
            provider = ImageProvider(R.drawable.next),
            contentDescription = "Navigate to the next game in the list.",
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
        modifier = modifier
            .width(100.dp)
            .height(80.dp)
            .background(Color.Red),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No games!")
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
        Text(text = name)
        Spacer(modifier = GlanceModifier.height(2.dp))
        if (score.isNotEmpty()) {
            Text(text = score)
            Spacer(modifier = GlanceModifier.height(2.dp))
        }
    }
}
