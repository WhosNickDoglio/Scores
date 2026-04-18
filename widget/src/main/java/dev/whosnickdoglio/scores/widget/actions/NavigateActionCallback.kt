// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget.actions

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback

class NavigateActionCallback : ActionCallback {

    private enum class Direction {
        UP,
        DOWN
    }

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        //        val direction: Direction? = parameters[navKey]
        //
        //        updateAppWidgetState(
        //            context = context,
        //            definition = ScoresStateDefinition,
        //            glanceId = glanceId
        //        ) { currentState ->
        //            val currentIndex = currentState.currentIndex ?: 0
        //
        //            // TODO more robust logic and handling of this
        //            // TODO wrapping around
        //            val newIndex =
        //                when (direction) {
        //                    Direction.DOWN ->
        //                        if (currentIndex == currentState.games.lastIndex) {
        //                            0
        //                        } else {
        //                            currentIndex + 1
        //                        }
        //                    Direction.UP -> if (currentIndex == 0) 0 else currentIndex - 1
        //                    null -> currentState.currentIndex
        //                }
        //
        //            return@updateAppWidgetState ScoresWidgetState(newIndex, currentState.games)
        //        }
        //
        //        ScoresWidget().update(context, glanceId)
    }

    companion object {
        private val navKey = ActionParameters.Key<Direction>("direction")

        fun up() =
            actionRunCallback<NavigateActionCallback>(actionParametersOf(navKey to Direction.UP))

        fun down() =
            actionRunCallback<NavigateActionCallback>(actionParametersOf(navKey to Direction.DOWN))
    }
}
