package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class HabitsWorkerFactory @Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            HabitsWorker::class.qualifiedName -> {
                val childWorkerFactory = workerProviders[HabitsWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }

            else ->
                null
        }
    }
}