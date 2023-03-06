package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class HabitAlarmReceiver : BroadcastReceiver() {


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

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        fun newIntent(context: Context): Intent {
            return Intent(context, HabitAlarmReceiver::class.java)
        }
    }
}

