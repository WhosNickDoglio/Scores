// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

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
import dev.whosnickdoglio.nba.api.models.Game
import dev.whosnickdoglio.scores.widget.theme.ScoresWidgetTheme

/**  */
@Composable
internal fun GameInfo(
    game: Game, // TODO make a sport agnostic game model at some point
    modifier: GlanceModifier = GlanceModifier,
) {
    // TODO
    //  - Don't show scores if game hasn't started
    //  - Add a LIVE text for when a game is live
    Row(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically) {
            // Team logos? Where can I get these?
            // TODO Make this less ugly and handle more info
            Text(text = game.homeTeam?.teamName.orEmpty(), style = ScoresWidgetTheme.textStyle)
            Spacer(modifier = GlanceModifier.width(6.dp))
            if (game.period != 0) {
                Text(
                    text = game.homeTeam?.score.toString(),
                    style = ScoresWidgetTheme.scoreTextStyle)
            }
            Spacer(modifier = GlanceModifier.width(6.dp))
            Text(
                text = game.gameStatus.toString(),
                style = ScoresWidgetTheme.textStyle) // More info on game
            Spacer(modifier = GlanceModifier.width(6.dp))

            if (game.period != 0) {
                Text(
                    text = game.awayTeam?.score.toString(),
                    style = ScoresWidgetTheme.scoreTextStyle)
            }
            Spacer(modifier = GlanceModifier.width(6.dp))
            Text(text = game.awayTeam?.teamName.orEmpty(), style = ScoresWidgetTheme.textStyle)
        }
}
