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

package dev.whosnickdoglio.nba

import com.slack.eithernet.ApiResult
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import com.squareup.moshi.Moshi
import dagger.Lazy
import dev.whosnickdoglio.nba.models.GameResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

/**
 *
 */
interface BallDontLieService {

    /**
     *
     *
     * https://www.balldontlie.io/#get-all-games
     */
    @GET("games")
    suspend fun retrieveGameData(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = null,
        @Query("dates") dates: List<LocalDate>? = null,
        @Query("seasons") seasons: List<Int>? = null,
        @Query("team_ids") teamIds: List<Int>? = null,
        @Query("postseason") postseason: Boolean? = null,
        @Query("start_date") startDate: LocalDate? = null,
        @Query("end_date") endDate: LocalDate? = null,
    ): ApiResult<GameResponse, Unit> // TODO custom error type
    // https://github.com/slackhq/EitherNet#decoding-error-bodies

    companion object {
        private const val BASE_URL = "https://www.balldontlie.io/api/v1/"

        /**
         *
         */
        fun create(
            moshi: Moshi,
            okHttpClient: Lazy<OkHttpClient>
        ): BallDontLieService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory { okHttpClient.get().newCall(it) }
            .addConverterFactory(ApiResultConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResultCallAdapterFactory)
            .build()
            .create()
    }
}
