// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT
rootProject.name = "Scores"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

plugins {
    id("com.gradle.develocity") version ("4.4.0")
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "2.6.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

include(
    ":app",
    ":app-theme",
    ":nba-api",
    ":inject-scopes",
    ":widget-ui",
    ":widget-theme",
    ":startup",
    ":workmanager-assisted",
    ":widget",
)
