// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.composeGuard) apply false
    alias(libs.plugins.metro) apply false
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.doctor)
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.android.library) apply false
}


// https://docs.gradle.org/8.9/userguide/gradle_daemon.html#daemon_jvm_criteria
tasks.updateDaemonJvm.configure {
    languageVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
    vendor = JvmVendorSpec.AZUL
}
