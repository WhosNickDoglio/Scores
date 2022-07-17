@file:Suppress("CommentSpacing")
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

package dev.whosnickdoglio.scores.widget.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.anvil.annotations.ContributesMultibinding
import dagger.MapKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.whosnickdoglio.anvil.AppScope
import dev.whosnickdoglio.nba.BallDontLieService
import kotlin.reflect.KClass

//
// https://commonsware.com/blog/2018/11/24/workmanager-app-widgets-side-effects.html
// https://stackoverflow.com/questions/70654474/starting-workmanager-task-from-appwidgetprovider-results-in-endless-onupdate-cal
class UpdateScoresWorker @AssistedInject constructor(
    private val service: BallDontLieService,
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        service.retrieveGameData()

        return TODO()
    }
}

@ContributesMultibinding(scope = AppScope::class, boundType = AssistedWorkerFactory::class)
@BindingKey(UpdateScoresWorker::class)
@AssistedFactory
interface UpdateScoresFactory : AssistedWorkerFactory

@MapKey
annotation class BindingKey(val value: KClass<out CoroutineWorker>)

interface AssistedWorkerFactory {
    fun createWorker(appContext: Context, workerParams: WorkerParameters): UpdateScoresWorker
}

//class ScoresWorkerFactory @Inject constructor(
//    private val assistedWorkerFactories: Map<KClass<out CoroutineWorker>, @JvmSuppressWildcards AssistedWorkerFactory>
//) : WorkerFactory() {
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParams: WorkerParameters
//    ): ListenableWorker? {
//        val clazz =
//            Class.forName(workerClassName).kotlin ?: return null // handle rename or deletion of worker
//
//        val factory =
//            assistedWorkerFactories[clazz] ?: assistedWorkerFactories.entries.firstOrNull {
//                clazz.java.isAssignableFrom(it.key.java)
//            }?.value ?: return null // delegate to default if not found
//
//        return factory.createWorker(appContext, workerParams)
//    }
//}
