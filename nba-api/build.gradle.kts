// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.kmp)
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
            api(libs.serialization)

            implementation(project(":inject-scopes"))
            implementation(libs.coroutines.core)
            implementation(libs.kermit)
            // TODO investigate using different engines per platform
            implementation(libs.ktor.cio)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.logging)
        }
    }
}
