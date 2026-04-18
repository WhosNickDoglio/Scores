// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.nba.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Period(
    @SerialName("period")
    val period: Int? = null, // 1
    @SerialName("periodType")
    val periodType: String? = null, // REGULAR
    @SerialName("score")
    val score: Int? = null // 13
)
