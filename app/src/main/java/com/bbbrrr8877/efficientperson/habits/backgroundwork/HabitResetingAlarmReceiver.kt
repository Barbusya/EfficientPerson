package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class HabitResetingAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {

            val workManager = WorkManager.getInstance(context)
            workManager.enqueueUniqueWork(
                HabitWorker.SIMPLE_WORKER_TAG,
                ExistingWorkPolicy.KEEP,
                HabitWorker.createWorkRequest(Data.EMPTY)
            )

        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, HabitResetingAlarmReceiver::class.java)
        }
    }
}

