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
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.text.Text
import dev.whosnickdoglio.nba.models.Game
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
