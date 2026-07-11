// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.convention.app) apply false
    alias(libs.plugins.convention.android.library) apply false
    alias(libs.plugins.convention.jvm) apply false
    alias(libs.plugins.convention.kmp) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.cacheFix) apply false
    alias(libs.plugins.dependencyGuard) apply false
    alias(libs.plugins.licensee) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktfmt) apply false
    alias(libs.plugins.composeGuard) apply false
    alias(libs.plugins.metro) apply false
    alias(libs.plugins.sqldelight) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.sortDependencies) apply false
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.doctor)
    alias(libs.plugins.gradle.versions)
}

doctor {
    javaHome {
        failOnError = false
    }
}

// https://docs.gradle.org/8.9/userguide/gradle_daemon.html#daemon_jvm_criteria
tasks.updateDaemonJvm.configure {
    languageVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
    vendor = JvmVendorSpec.AZUL
}
