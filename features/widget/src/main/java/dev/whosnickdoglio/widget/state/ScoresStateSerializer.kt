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

package dev.whosnickdoglio.widget.state

import androidx.datastore.core.Serializer
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okio.buffer
import okio.sink
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * A DataStore [Serializer] for the [ScoresWidgetState] object.
 *
 * @param moshi A [Moshi] instance that parses the [ScoresWidgetState]
 */
class ScoresStateSerializer @Inject constructor(
    moshi: Moshi,
) : Serializer<ScoresWidgetState> {

    private val adapter = moshi.adapter<ScoresWidgetState>()

    override val defaultValue: ScoresWidgetState = ScoresWidgetState()

    @Suppress("SwallowedException") // TODO come back to this
    override suspend fun readFrom(input: InputStream): ScoresWidgetState = try {
        adapter.fromJson(input.bufferedReader().readText()) ?: ScoresWidgetState()
    } catch (exception: EOFException) {
        ScoresWidgetState()
    }

    override suspend fun writeTo(t: ScoresWidgetState, output: OutputStream) {
        val sink = output.sink().buffer()
        adapter.toJson(sink, t)
        sink.close()
    }
}
