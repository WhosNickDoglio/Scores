// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins { alias(libs.plugins.convention.android.library) }

android { buildFeatures { buildConfig = true } }

dependencies { implementation(libs.androidx.startup) }
