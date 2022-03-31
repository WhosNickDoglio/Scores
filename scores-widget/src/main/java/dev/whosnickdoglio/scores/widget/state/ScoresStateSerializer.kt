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

package dev.whosnickdoglio.scores.widget.state

import androidx.datastore.core.Serializer
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dev.whosnickdoglio.anvil.AppScope
import dev.whosnickdoglio.nba.models.Game
import dev.whosnickdoglio.nba.state.ScoresState
import okio.buffer
import okio.sink
import okio.source
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

object ScoresStateSerializer : Serializer<ScoresState> {

    private val moshi = Moshi.Builder().build()

    private val adapter = moshi.adapter<ScoresState>()

    override val defaultValue: ScoresState = ScoresState()

    override suspend fun readFrom(input: InputStream): ScoresState =
        adapter.fromJson(input.source().buffer()) ?: ScoresState()

    override suspend fun writeTo(t: ScoresState, output: OutputStream) {
        adapter.toJson(output.sink().buffer(), t)
    }
}

