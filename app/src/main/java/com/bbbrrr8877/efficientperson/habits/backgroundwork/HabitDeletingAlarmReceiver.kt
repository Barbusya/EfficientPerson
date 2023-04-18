package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class HabitDeletingAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {

            val workManager = WorkManager.getInstance(context)
            workManager.enqueueUniqueWork(
                DeletingHabitWorker.DELETING_WORKER_TAG,
                ExistingWorkPolicy.APPEND_OR_REPLACE,
                DeletingHabitWorker.createWorkRequest(habitId)
            )
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, HabitDeletingAlarmReceiver::class.java)
        }
    }
}

