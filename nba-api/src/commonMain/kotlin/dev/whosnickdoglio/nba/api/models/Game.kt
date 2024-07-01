/*
 * MIT License
 *
 * Copyright (c) 2024 Nicholas Doglio
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

package dev.whosnickdoglio.nba.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val gameId: String? = null, // 1022400106
    val gameCode: String? = null, // 20240630/ATLNYL
    val gameStatus: Int? = null, // 3
    val gameStatusText: String? = null, // Final
    val period: Int? = null, // 4
    val gameClock: String? = null,
    val gameTimeUTC: String? = null, // 2024-06-30T17:00:00Z
    val gameEt: String? = null, // 2024-06-30T13:00:00Z
    val regulationPeriods: Int? = null, // 4
    val ifNecessary: Boolean? = null, // false
    val seriesGameNumber: String? = null,
    val gameLabel: String? = null, // Regular Season
    val gameSubLabel: String? = null,
    val seriesText: String? = null,
    val seriesConference: String? = null,
    val poRoundDesc: String? = null,
    val gameSubtype: String? = null,
    val homeTeam: Team? = null,
    val awayTeam: Team? = null,
)
