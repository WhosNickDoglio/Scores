// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.ui

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.text.Text
import dev.whosnickdoglio.nba.api.models.Game
import dev.whosnickdoglio.scores.widget.theme.ScoresWidgetTheme

/**
 */
@Composable
fun SingleGame(
    onRefresh: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateDown: () -> Unit,
    modifier: GlanceModifier = GlanceModifier,
    game: Game? = null
) {
    Row(modifier = modifier) {
        Column(
            modifier = GlanceModifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Refresh(onRefresh = onRefresh)
        }

        if (game != null) {
            GameInfo(modifier = GlanceModifier.fillMaxHeight().defaultWeight(), game = game)
        } else {
            Row(
                modifier = GlanceModifier.fillMaxHeight().defaultWeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.no_games), style = ScoresWidgetTheme.textStyle)
            }
        }

        if (game != null) {
            NavigationColumn(onNavigateUp = onNavigateUp, onNavigateDown = onNavigateDown)
        }
    }
}

@Composable
private fun NavigationColumn(
    onNavigateUp: () -> Unit,
    onNavigateDown: () -> Unit,
    modifier: GlanceModifier = GlanceModifier,
) {
    Column(modifier = modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            provider = ImageProvider(R.drawable.up),
            contentDescription = stringResource(R.string.cd_navigation_previous),
            modifier = GlanceModifier.clickable(onNavigateUp)
        )
        Image(
            provider = ImageProvider(R.drawable.down),
            contentDescription = stringResource(R.string.cd_navigation_next),
            modifier = GlanceModifier.clickable(onNavigateDown)
        )
    }
}
