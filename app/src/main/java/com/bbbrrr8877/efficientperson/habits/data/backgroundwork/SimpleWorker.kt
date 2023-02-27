package com.bbbrrr8877.efficientperson.habits.data.backgroundwork

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.*
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase

class SimpleWorker(
    private val context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val dao = HabitDatabase.getInstance(Application()).habitListDao()

        dao.getHabitList()
            .collect {
                for (item in it) {
                    val newItem = item.copy(
                        isDone = false,
                    )
                    val habitItem = dao.addHabitItem(newItem)
                    Log.d("SimpleWorker", habitItem.toString())
                }
                cancelWork(context)
            }
        return Result.success()
    }

    companion object {

        const val SIMPLE_WORKER_TAG = "SimpleWorkerTag"

        fun createWorkRequest(data: Data): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInputData(data)
                .addTag(SIMPLE_WORKER_TAG)
                .build()
        }

        fun cancelWork(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(SIMPLE_WORKER_TAG)
        }
    }
}