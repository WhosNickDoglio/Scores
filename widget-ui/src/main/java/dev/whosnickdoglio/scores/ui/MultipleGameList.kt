// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.padding
import androidx.glance.text.Text
import dev.whosnickdoglio.nba.api.models.Game
import dev.whosnickdoglio.scores.widget.theme.ScoresWidgetTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import widget.ui.R

@Composable
public fun MultipleGameList(
    onRefresh: () -> Unit,
    modifier: GlanceModifier = GlanceModifier,
    games: ImmutableList<Game> = emptyList<Game>().toImmutableList(),
) {
    if (games.isEmpty()) {
        Column(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.no_games), style = ScoresWidgetTheme.textStyle)
            Refresh(modifier = GlanceModifier.padding(4.dp), onRefresh = onRefresh)
        }
    } else {
        Row(modifier = modifier) {
            // TODO clean this up
            Refresh(onRefresh = onRefresh)
            LazyColumn(modifier = GlanceModifier.defaultWeight()) {
                items(games) { game -> GameInfo(game = game) }
            }
        }
    }
}
