/*
 * MIT License
 *
 * Copyright (c) 2023 Nicholas Doglio
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

package dev.whosnickdoglio.scores.plugins

import dev.whosnickdoglio.scores.plugins.configurations.AndroidLibraryConfiguration
import dev.whosnickdoglio.scores.plugins.configurations.CommonDependencyConfiguration
import dev.whosnickdoglio.scores.plugins.configurations.ComposeGuardConfiguration
import dev.whosnickdoglio.scores.plugins.configurations.ConfigurablePlugin
import dev.whosnickdoglio.scores.plugins.configurations.Configuration
import dev.whosnickdoglio.scores.plugins.configurations.LintPluginConfiguration
import dev.whosnickdoglio.scores.plugins.configurations.JvmTaskConfiguration
import dev.whosnickdoglio.scores.plugins.configurations.SpotlessConfiguration
import org.gradle.api.Project

class AndroidLibPlugin : ConfigurablePlugin() {
    override val configurations: List<Configuration> =
        listOf(
            SpotlessConfiguration(),
            JvmTaskConfiguration(),
            LintPluginConfiguration(),
            CommonDependencyConfiguration(),
            AndroidLibraryConfiguration(),
            ComposeGuardConfiguration(),
        )

    override fun apply(target: Project) {
        target.pluginManager.run {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("org.gradle.android.cache-fix")
        }
        super.apply(target)
    }
}
