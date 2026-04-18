// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    id("scores.android")
    alias(libs.plugins.metro)
}

android { namespace = "dev.whosnickdoglio.workmanager" }

dependencies { implementation(libs.androidx.workmanager) }
