/*
 * MIT License
 *
 * Copyright (c) 2022 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.cacheFix)
    alias(libs.plugins.sortDependencies)
    alias(libs.plugins.spotless)
    alias(libs.plugins.licensee)
    alias(libs.plugins.composeGuard)
}

licensee {
    allow("Apache-2.0")
    allow("BSD-3-Clause")
    allow("MIT")
}

kotlin { jvmToolchain(21) }

tasks.withType<com.diffplug.gradle.spotless.SpotlessTask>().configureEach {
    notCompatibleWithConfigurationCache("https://github.com/diffplug/spotless/issues/987")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {

    // https://github.com/diffplug/spotless/issues/1527
    // https://github.com/diffplug/spotless/issues/1644
    lineEndings = com.diffplug.spotless.LineEnding.PLATFORM_NATIVE

    format("misc") {
        target("*.md", ".gitignore")
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

android {
    compileSdk = libs.versions.compile.sdk.get().toInt()
    namespace = "dev.whosnickdoglio.scores"

    defaultConfig {
        applicationId = "dev.whosnickdoglio.scores"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures { buildConfig = true }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        freeCompilerArgs += listOf("-opt-in=kotlin.ExperimentalStdlibApi")
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    lint {
        disable.addAll(setOf("GradleDependency", "ObsoleteLintCustomCheck"))
        htmlReport = false
        xmlReport = false
        textReport = true
        absolutePaths = false
        checkTestSources = true
        warningsAsErrors = true
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.glance.appwidget)
    implementation(libs.kotlinInject)
    implementation(projects.appTheme)
    implementation(projects.injectScopes)
    implementation(projects.nbaApi)
    implementation(projects.startup)
    implementation(projects.widget)
    implementation(projects.widgetUi)
    implementation(projects.workmanagerAssisted)

    testImplementation(libs.assertk)
    testImplementation(libs.junit)

    coreLibraryDesugaring(libs.desugar)

    ksp(libs.kotlinInject.compiler)

    lintChecks(libs.lints.compose)
}
