// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT
rootProject.name = "Scores"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.gradle.develocity") version ("4.5.1")
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "2.8.0"
    id("com.fueledbycaffeine.spotlight") version "1.7.1"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}
