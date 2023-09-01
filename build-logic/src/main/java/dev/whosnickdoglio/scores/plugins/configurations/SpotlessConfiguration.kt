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

package dev.whosnickdoglio.scores.plugins.configurations

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

internal class SpotlessConfiguration : Configuration {
    override fun configure(project: Project) =
        with(project) {
            pluginManager.apply("com.diffplug.spotless")

            val catalog =
                extensions.findByType(VersionCatalogsExtension::class.java)
                    ?: error("No Catalog found!")

            val libs = catalog.named("libs")

            extensions.getByType(SpotlessExtension::class.java).run {
                format("misc") { formatExt ->
                    formatExt.target("*.md", ".gitignore")
                    formatExt.trimTrailingWhitespace()
                    formatExt.endWithNewline()
                }

                kotlin { ktExt ->
                    ktExt.ktfmt(libs.findVersion("ktfmt").get().requiredVersion).kotlinlangStyle()
                    ktExt.trimTrailingWhitespace()
                    ktExt.endWithNewline()
                    ktExt.targetExclude("**/build/generated/**")
                }
                kotlinGradle { ktsExt ->
                    ktsExt.ktfmt(libs.findVersion("ktfmt").get().requiredVersion).kotlinlangStyle()
                    ktsExt.trimTrailingWhitespace()
                    ktsExt.endWithNewline()
                }
            }
        }
}
