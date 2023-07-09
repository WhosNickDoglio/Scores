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

package dev.whosnickdoglio.scores.dagger

import javax.inject.Scope
import kotlin.reflect.KClass

/*
Stolen from https://github.com/gpeal/Anvil-Sample/blob/main/lib.daggerscopes/src/main/java/com/gpeal/droidconanvilsample/lib/daggerscopes/SingleIn.kt
 */

/**
 * This annotation lets you use the same annotation to represent which scope you want to contribute
 * an Anvil object to and also as `@SingleIn(YourScope::class)`.
 *
 * Without `@SingleIn`, an AppComponent contribution might look like this:
 * ```
 * @Singleton
 * @ContributesBinding(AppScope::class)
 * class YourClassImpl : YourClass
 * ```
 *
 * Singleton is a well defined pattern for AppScope but the scope naming becomes more confusing once
 * you start defining your own components. `@SingleIn` prevents you from memorizing two names per
 * component. The above example becomes:
 * ```
 * @SingleIn(AppScope::class)
 * @ContributesBinding(AppScope::class)
 * class YourClassImpl : YourClass
 * ```
 *
 * And custom components would look like:
 * ```
 * @SingleIn(YourScope::class)
 * @ContributesBinding(YourScope::class)
 * class YourClassImpl : YourClass
 * ```
 */
@Scope @Retention(AnnotationRetention.RUNTIME) annotation class SingleIn(val clazz: KClass<*>)
