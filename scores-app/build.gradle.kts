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
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.play.publish)
    alias(libs.plugins.detekt)
    alias(libs.plugins.anvil)
    alias(libs.plugins.moshiIr)
    alias(libs.plugins.ktfmt)
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
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        freeCompilerArgs += listOf("-opt-in=kotlin.ExperimentalStdlibApi")
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(platform(libs.compose.bom))

    lintChecks(libs.lints.compose)

    coreLibraryDesugaring(libs.desguar)
    
    implementation(projects.libraries.nbaApi)
    implementation(projects.libraries.appScope)
    implementation(projects.libraries.widgetUi)

    // widget stuff
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.glance.appwidget)

    implementation(libs.androidx.workmanager)
    androidTestImplementation(libs.androidx.workmanager.test)

    implementation(libs.androidx.datastore)
    implementation(libs.immutableCollections)

    implementation(libs.moshi)

    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)

    // app stuff
    implementation(libs.androidx.material)
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.androidx.core)
    implementation(libs.timber)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}
