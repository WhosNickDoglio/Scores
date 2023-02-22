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

import com.squareup.moshi.JsonClass
import dev.whosnickdoglio.nba.models.Game

// TODO move this out of this module
/**
 * A data class that represents all the relevant state information for the Scores widget.
 *
 * @param currentIndex The index of the game within the [games] [List] currently being shown by the
 *   widget if the widget is setup to only show a single game, defaults to `null`.
 * @param games A list of all the [Games][Game] that could be shown in the widget, defaults to an
 *   empty list.
 */
@JsonClass(generateAdapter = true)
data class ScoresWidgetState(
    val currentIndex: Int? = null,
    val games: List<Game> = emptyList(),
    val areThereGamesToday: Boolean = true,
// TODO theming stuff
)
