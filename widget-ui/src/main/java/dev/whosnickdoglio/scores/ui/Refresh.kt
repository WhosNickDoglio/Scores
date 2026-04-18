// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.ui

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable

@Composable
internal fun Refresh(onRefresh: () -> Unit, modifier: GlanceModifier = GlanceModifier) {
    Image(
        provider = ImageProvider(R.drawable.refresh),
        contentDescription = stringResource(R.string.cd_refresh),
        modifier = modifier.clickable(onRefresh)
    )
}
