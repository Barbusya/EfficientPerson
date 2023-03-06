package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase

class MyWorkerFactory(
    private val habitDatabase: HabitDatabase.Companion
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when(workerClassName) {
            HabitWorker::class.java.name ->
                HabitWorker(appContext, workerParameters, habitDatabase)
            else ->
                null
        }
    }
}