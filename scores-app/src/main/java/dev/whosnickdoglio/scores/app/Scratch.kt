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

package dev.whosnickdoglio.scores.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.whosnickdoglio.nba.models.Game
import dev.whosnickdoglio.nba.models.Team
import dev.whosnickdoglio.scores.ui.R

// TODO clean this up
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    game: Game? = null
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(100.dp)
            .height(80.dp)
    ) {
        Row(modifier = Modifier.height(40.dp)) {
            val chipContentHeight = Modifier.height(50.dp)
            if (game != null) {
                TeamColumn(
                    modifier = chipContentHeight,
                    name = game.homeTeam.abbreviation,
                    score = game.homeTeamScore.toString()
                )
                Spacer(modifier = Modifier.width(10.dp))
                TeamColumn(
                    modifier = chipContentHeight,
                    name = game.visitorTeam.abbreviation,
                    score = game.visitorTeamScore.toString()
                )
            } else {
                EmptyChip()
            }
        }

        if (game != null) {
            Text(text = game.status) // More info on game
            Spacer(Modifier.height(2.dp))
            NavigationRow(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun EmptyChip(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(100.dp)
            .height(80.dp)
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No games!")
        Image(
            painter = painterResource(id = R.drawable.refresh),
            contentDescription = "Button to refresh game scores data",
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
private fun TeamColumn(
    modifier: Modifier = Modifier,
    name: String,
    score: String
) {
    Column(
        modifier = modifier.wrapContentWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = name)
        Spacer(modifier = Modifier.height(2.dp))
        if (score.isNotEmpty()) {
            Text(text = score)
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
private fun NavigationRow(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    onNavigateDown: () -> Unit = {},
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Navigate to the previous game in the list.",
            modifier = Modifier.clickable { onNavigateUp() }
        )
        Image(
            painter = painterResource(id = R.drawable.next),
            contentDescription = "Navigate to the next game in the list.",
            modifier = Modifier.clickable { onNavigateDown() }
        )
    }
}

private val finishedGame = Game(
    date = "",
    homeTeam = Team(
        abbreviation = "NYK",
        city = "New York",
        conference = "East",
        division = "Atlanta",
        fullName = "New York Knicks",
        id = 1,
        name = "Knicks"
    ),
    homeTeamScore = 200,
    id = 100,
    period = 2,
    postseason = false,
    season = 2020,
    status = "FINAL",
    time = "5:43",
    visitorTeam = Team(
        abbreviation = "TOR",
        city = "Toronto",
        conference = "East",
        division = "Atlanta",
        fullName = "Toronto Raptors",
        id = 2,
        name = "Raptors"
    ),
    visitorTeamScore = 150,
)

private val inProgressGame = Game(
    date = "",
    homeTeam = Team(
        abbreviation = "NYK",
        city = "New York",
        conference = "East",
        division = "Atlanta",
        fullName = "New York Knicks",
        id = 1,
        name = "Knicks"
    ),
    homeTeamScore = 200,
    id = 100,
    period = 2,
    postseason = false,
    season = 2020,
    status = "3Q",
    time = "5:43",
    visitorTeam = Team(
        abbreviation = "TOR",
        city = "Toronto",
        conference = "East",
        division = "Atlanta",
        fullName = "Toronto Raptors",
        id = 2,
        name = "Raptors"
    ),
    visitorTeamScore = 150,
)

private val yetToStartGame = Game(
    date = "",
    homeTeam = Team(
        abbreviation = "NYK",
        city = "New York",
        conference = "East",
        division = "Atlanta",
        fullName = "New York Knicks",
        id = 1,
        name = "Knicks"
    ),
    homeTeamScore = 0,
    id = 100,
    period = 2,
    postseason = false,
    season = 2020,
    status = "7:00 pm ET",
    time = "",
    visitorTeam = Team(
        abbreviation = "TOR",
        city = "Toronto",
        conference = "East",
        division = "Atlanta",
        fullName = "Toronto Raptors",
        id = 2,
        name = "Raptors"
    ),
    visitorTeamScore = 0,
)

@Preview
@Composable
fun PreviewChip() {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Final
            Chip(game = finishedGame, modifier = Modifier.background(Color.Blue))
            Spacer(Modifier.height(50.dp))
            // In Progress
            Chip(game = inProgressGame, modifier = Modifier.background(Color.Yellow))
            Spacer(Modifier.height(50.dp))
            // Not started yet
            Chip(game = yetToStartGame, modifier = Modifier.background(Color.Cyan))
            Spacer(Modifier.height(50.dp))
        }
    }
}

