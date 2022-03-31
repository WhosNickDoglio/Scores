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

package dev.whosnickdoglio.nba.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

/**
 * @param date
 * @param homeTeam
 * @param homeTeamScore
 * @param id
 * @param period
 * @param postseason
 * @param season
 * @param status
 * @param time
 * @param visitorTeam
 * @param visitorTeamScore
 */
@JsonClass(generateAdapter = true)
data class Game(
    val date: String, // TODO convert to LocalDate will need adapter
    @Json(name = "home_team") val homeTeam: Team,
    @Json(name = "home_team_score") val homeTeamScore: Int,
    val id: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    @Json(name = "visitor_team") val visitorTeam: Team,
    @Json(name = "visitor_team_score") val visitorTeamScore: Int
)
