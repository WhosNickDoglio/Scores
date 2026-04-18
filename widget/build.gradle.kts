// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    id("scores.android")
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android { namespace = "dev.whosnickdoglio.scores.widget" }

dependencies {
    api(libs.androidx.workmanager)

    implementation(libs.glance.appwidget)
    implementation(libs.immutableCollections)
    implementation(libs.serialization)
    implementation(projects.injectScopes)
    implementation(projects.nbaApi)
    implementation(projects.widgetTheme)
    implementation(projects.widgetUi)
    implementation(projects.workmanagerAssisted)
}
