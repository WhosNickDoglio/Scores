// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
}

kotlin {
    jvm()

    sourceSets {
        jvmMain.dependencies {
            implementation(project.dependencies.platform(libs.kotlin.bom))
            implementation(project.dependencies.platform(libs.ktor.bom))
        }

        commonMain.dependencies {
            api(libs.ktor.core)
            api(libs.ktor.serialization)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.contentNegotiation)
            // TODO investigate using different engines per platform
            implementation(libs.ktor.cio)
            api(libs.serialization)
            implementation(libs.kermit)
            implementation(libs.coroutines.core)
            implementation(projects.injectScopes)
        }
    }
}
