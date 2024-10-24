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
data class Team(
    @SerialName("teamId")
    val teamId: Int? = null, // 1611661330
    @SerialName("teamName")
    val teamName: String? = null, // Dream
    @SerialName("teamCity")
    val teamCity: String? = null, // Atlanta
    @SerialName("teamTricode")
    val teamTricode: String? = null, // ATL
    @SerialName("wins")
    val wins: Int? = null, // 7
    @SerialName("losses")
    val losses: Int? = null, // 10
    @SerialName("score")
    val score: Int? = null, // 75
    @SerialName("seed")
    val seed: Int? = null, // null
    @SerialName("inBonus")
    val inBonus: Int? = null, // null
    @SerialName("timeoutsRemaining")
    val timeoutsRemaining: Int? = null, // 0
    @SerialName("periods")
    val periods: List<Period>? = null
)
