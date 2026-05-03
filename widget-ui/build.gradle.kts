// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
}

convention.published { composeGuard() }

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(libs.compose.material)
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material)
    implementation(libs.immutableCollections)
    implementation(projects.nbaApi)
    implementation(projects.widgetTheme)
}
