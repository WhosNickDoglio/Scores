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

package dev.whosnickdoglio.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.glance.action.Action
import dev.whosnickdoglio.scores.ui.ScoresChip

@Composable
fun WidgetSettings(modifier: Modifier = Modifier) {
    val action = object : Action {

    }

    Column(modifier = modifier) {
        // If this works make it a ViewPager to see all the sizes?
        ScoresChip(
            onRefresh = action,
            onNavigateUp = action,
            onNavigateDown = action,
            game = null
        )
        /**
         * TODO
         *  - Preview of Widget as currently styled (Like Weather Timeline)
         *  - Theme Options (Material You, Palette API?, hardcoded colors (Compose color picker)
         *  - League options (NBA, WNBA, maybe more?)
         *  - Favorite team selection? (Can filter results by favorite team only)
         *  - About (Licenses, Github link, versions, etc)
         */
    }
}
