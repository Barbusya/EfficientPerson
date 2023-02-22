package com.bbbrrr8877.efficientperson.habits.data.backgroundwork

import android.content.Context
import android.util.Log
import androidx.work.*
import com.bbbrrr8877.efficientperson.habits.domain.usecases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.GetHabitListUseCase

class HabitWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters,
    private val editHabitItemUseCase: EditHabitItemUseCase,
    private val getHabitListUseCase: GetHabitListUseCase,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        log("doWork")
//        val page = workerParameters.inputData.getInt(PAGE, 0)
//        for (i in 0 until 5) {
//            Thread.sleep(1000)
//            log("Timer $i $page")
//        }

        return Result.success()
    }



    private fun log(message: String) {
        Log.d("HabitWorker", "Work request $message")
    }

    companion object {

        private const val PAGE = "page"

        const val WORK_NAME = "work name"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<HabitWorker>()
                .setInputData(workDataOf(PAGE to page))
                .build()
        }
    }
}