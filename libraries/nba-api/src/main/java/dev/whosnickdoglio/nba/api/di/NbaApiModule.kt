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

package dev.whosnickdoglio.nba.api.di

import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dev.whosnickdoglio.anvil.AppScope
import dev.whosnickdoglio.nba.BallDontLieService
import dev.whosnickdoglio.nba.api.moshi.CustomJsonAdapter
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * A [dagger.Module] that is contributed to the [AppScope] that provides the necessary dependencies
 * to make network requests for NBA and WNBA game information.
 */
@ContributesTo(AppScope::class)
@Module
object NbaApiModule {

    @Singleton
    @Provides
    fun provideMoshi(adapters: Set<@JvmSuppressWildcards CustomJsonAdapter>): Moshi =
        Moshi.Builder().apply { adapters.forEach { adapter -> add(adapter) } }.build()

    @Singleton
    @Provides
    fun provideOkhttp(@Named("DEBUG") isDebugBuild: Boolean): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (isDebugBuild) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
                }
            }
            .build()

    @Singleton
    @Provides
    fun provideBallDontLieService(
        moshi: Moshi,
        okHttpClient: Lazy<OkHttpClient>
    ): BallDontLieService = BallDontLieService.create(moshi, okHttpClient)
}
