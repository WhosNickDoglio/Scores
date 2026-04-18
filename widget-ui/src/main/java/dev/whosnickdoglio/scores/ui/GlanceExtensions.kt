// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.glance.LocalContext

@Composable
fun stringResource(@StringRes resource: Int): String = LocalContext.current.getString(resource)
