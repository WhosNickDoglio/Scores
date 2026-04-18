// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins { id("scores.android") }

android {
    namespace = "dev.whosnickdoglio.scores.startup"
    buildFeatures { buildConfig = true }
}

dependencies { implementation(libs.androidx.startup) }
