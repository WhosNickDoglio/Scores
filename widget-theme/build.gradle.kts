// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    id("scores.android")
    alias(libs.plugins.kotlin.compose)
}

android { namespace = "dev.whosnickdoglio.scores.widget.theme" }

dependencies {
    implementation(libs.compose.material)
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material)
    implementation(libs.immutableCollections)
}
