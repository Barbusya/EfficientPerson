package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.app.Activity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitsBackgroundWork
import java.util.Calendar
import javax.inject.Inject

class HabitsBackgroundWorkImpl @Inject constructor() : HabitsBackgroundWork {


    override suspend fun startHabitsWorkManager(activity: Activity) {
        val currentTime = Calendar.getInstance()
        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        if (currentTime.after(targetTime))
            targetTime.add(Calendar.DAY_OF_MONTH, 1)

        val timeDiff = targetTime.timeInMillis - currentTime.timeInMillis

        val workManager = WorkManager.getInstance(activity)
        workManager.enqueueUniquePeriodicWork(
            HabitsWorker.HABITS_WORKER_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            HabitsWorker.createWorkRequest(timeDiff)
        )
    }
}