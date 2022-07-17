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
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import dev.whosnickdoglio.nba.models.Game

/**
 *
 */
@Composable
internal fun GameInfo(
    modifier: GlanceModifier = GlanceModifier,
    game: Game, // TODO make a sport agnostic game model at some point
) {
    // TODO
    //  - Don't show scores if game hasn't started
    //  - Add a LIVE text for when a game is live
    Row(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Team logos? Where can I get these?
        // TODO Make this less ugly and handle more info
        Text(text = game.homeTeam.name, style = ScoresWidgetTheme.textStyle)
        Spacer(modifier = GlanceModifier.width(6.dp))
        if (game.period != 0) {
            Text(text = game.homeTeamScore.toString(), style = ScoresWidgetTheme.scoreTextStyle)
        }
        Spacer(modifier = GlanceModifier.width(6.dp))
        Text(text = game.status, style = ScoresWidgetTheme.textStyle) // More info on game
        Spacer(modifier = GlanceModifier.width(6.dp))

        if (game.period != 0) {
            Text(text = game.visitorTeamScore.toString(), style = ScoresWidgetTheme.scoreTextStyle)
        }
        Spacer(modifier = GlanceModifier.width(6.dp))
        Text(text = game.visitorTeam.name, style = ScoresWidgetTheme.textStyle)
    }
}
