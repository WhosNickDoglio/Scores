// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import dev.zacsweers.metro.Inject
import java.io.File

@Inject
class ScoresStateDefinition(private val serializer: ScoresStateSerializer) :
    GlanceStateDefinition<ScoresWidgetState> {

    override suspend fun getDataStore(
        context: Context,
        fileKey: String
    ): DataStore<ScoresWidgetState> =
        DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile("$fileKey.json") }
        )

    override fun getLocation(context: Context, fileKey: String): File =
        context.dataStoreFile("$fileKey.json")
}
