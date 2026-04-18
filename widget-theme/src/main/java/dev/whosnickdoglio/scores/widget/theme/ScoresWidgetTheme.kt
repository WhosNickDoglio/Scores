// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceTheme
import androidx.glance.color.ColorProviders
import androidx.glance.material3.ColorProviders
import androidx.glance.text.FontWeight
import androidx.glance.text.TextStyle

/**  */
object ScoresWidgetTheme {

    // TODO use textColorPrimary or colorPrimary?

    val textStyle = TextStyle()

    val scoreTextStyle =
        TextStyle(
            fontWeight = FontWeight.Bold,
        )

    val colors: ColorProviders
        @GlanceComposable @Composable @ReadOnlyComposable get() = scoresColorProviders
}

private val lightColors: ColorScheme = TODO()
private val darkColors: ColorScheme = TODO()

private val scoresColorProviders = ColorProviders(lightColors, darkColors)

@Composable
fun ScoresWidgetTheme(content: @GlanceComposable @Composable () -> Unit) {
    GlanceTheme(colors = scoresColorProviders, content = content)
}
