// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.scores.widget

import androidx.datastore.core.Serializer
import dev.zacsweers.metro.Inject
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import kotlinx.serialization.json.Json

/**
 * A DataStore [Serializer] for the [ScoresWidgetState] object.
 *
 * @param json A [Json] instance that parses the [ScoresWidgetState]
 */
@Inject
public class ScoresStateSerializer(private val json: Json) : Serializer<ScoresWidgetState> {

    override val defaultValue: ScoresWidgetState = ScoresWidgetState()

    @Suppress("SwallowedException") // TODO come back to this
    override suspend fun readFrom(input: InputStream): ScoresWidgetState =
        try {
            json.decodeFromString(
                ScoresWidgetState.serializer(),
                input.readBytes().decodeToString(),
            )
        } catch (exception: EOFException) {
            ScoresWidgetState()
        }

    override suspend fun writeTo(t: ScoresWidgetState, output: OutputStream) {
        output.write(json.encodeToString(ScoresWidgetState.serializer(), t).encodeToByteArray())
    }
}
