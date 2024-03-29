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

package dev.whosnickdoglio.nba.moshi

import com.squareup.anvil.annotations.ContributesMultibinding
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import dev.whosnickdoglio.anvil.AppScope
import dev.whosnickdoglio.anvil.WidgetScope
import java.time.LocalDate
import javax.inject.Inject

@ContributesMultibinding(scope = AppScope::class, boundType = CustomJsonAdapter::class)
@ContributesMultibinding(scope = WidgetScope::class, boundType = CustomJsonAdapter::class)
class LocalDateAdapter @Inject constructor() : JsonAdapter<LocalDate>(), CustomJsonAdapter {

    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate? {
        if (reader.peek() == JsonReader.Token.NULL) {
            return reader.nextNull()
        }
        val string = reader.nextString()
        reader.close()
        return LocalDate.parse(string)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(value.toString())
        }
        writer.close()
    }
}
