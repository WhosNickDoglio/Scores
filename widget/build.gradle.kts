// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

convention.published { composeGuard() }

android {
    lint {
        // Weird crash with this rule?
        // https://scans.gradle.com/s/6yecx2kkqyifq
        disable.add("MutableCollectionMutableState")
    }
}

dependencies {
    api(libs.androidx.workmanager)

    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(project(":inject-scopes"))
    implementation(project(":nba-api"))
    implementation(project(":widget-theme"))
    implementation(project(":widget-ui"))
    implementation(project(":workmanager-assisted"))
    implementation(libs.glance.appwidget)
    implementation(libs.immutableCollections)
    implementation(libs.serialization)
}
