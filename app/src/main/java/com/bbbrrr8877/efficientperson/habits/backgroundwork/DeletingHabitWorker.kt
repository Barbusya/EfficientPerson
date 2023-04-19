package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase

class DeletingHabitWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters,
    private val habitDatabase: HabitDatabase.Companion,
) : CoroutineWorker(context, workerParameters) {

    private val dao = habitDatabase.getInstance(context.applicationContext).habitListDao()

    override suspend fun doWork(): Result {
        Log.d("DeletingItem", "work manager")
        try {
            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager

            createNotificationChannel(notificationManager)

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Habits")
                .setContentText("Habits deleting")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

            notificationManager.notify(NOTIFICATION_ID, notification)

            val habitId = workerParameters.inputData.getLong(HABIT_ID, 0L)
            dao.deleteHabitItem(habitId)

            return Result.success()

        } catch (e: Exception) {
            Log.e("DeletingHabitWorker", "Error deleting habits", e)
            return Result.failure()
        } finally {
            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.cancel(NOTIFICATION_ID)
        }
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {

        private const val HABIT_ID = "habit_id"
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 2
        const val DELETING_WORKER_TAG = "DeletingHabitWorkerTag"

        fun createWorkRequest(habitId: Long): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(DeletingHabitWorker::class.java)
                .setInputData(workDataOf(HABIT_ID to habitId))
                .addTag(DELETING_WORKER_TAG)
                .build()
        }

        fun cancelWork(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(DELETING_WORKER_TAG)
        }
    }
}

//TODO DI of WorkManager
//TODO Notification Text, Title and Icon