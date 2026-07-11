// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.app)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.licensee)
    alias(libs.plugins.composeGuard)
    alias(libs.plugins.metro)
}

convention {
    published {
        dependencyGuard()
        composeGuard()
    }
}

licensee {
    allow("Apache-2.0")
    allow("BSD-3-Clause")
    allowUrl("https://opensource.org/license/mit")
}

android {
    defaultConfig {
        applicationId = "dev.whosnickdoglio.scores"
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(project(":app-theme"))
    implementation(project(":inject-scopes"))
    implementation(project(":nba-api"))
    implementation(project(":startup"))
    implementation(project(":widget"))
    implementation(project(":widget-ui"))
    implementation(project(":workmanager-assisted"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.glance.appwidget)

    testImplementation(libs.assertk)
    testImplementation(libs.junit)

    coreLibraryDesugaring(libs.desugar)

    lintChecks(libs.lints.compose)
}
