package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class HabitDeletingAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("DeletingItem", "receiver")
        context?.let {
            intent?.let {
                val habitId = intent.getLongExtra("habit_id_from_alarm", 0)
                val workManager = WorkManager.getInstance(context)
                workManager.enqueueUniqueWork(
                    DeletingHabitWorker.DELETING_WORKER_TAG,
                    ExistingWorkPolicy.APPEND_OR_REPLACE,
                    DeletingHabitWorker.createWorkRequest(habitId)
                )
            }
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, HabitDeletingAlarmReceiver::class.java)
        }
    }
}

