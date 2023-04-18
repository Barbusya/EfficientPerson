package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.*
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase

class UpdatingHabitWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters,
    private val habitDatabase: HabitDatabase.Companion,
) : CoroutineWorker(context, workerParameters) {

    private val dao = habitDatabase.getInstance(context.applicationContext).habitListDao()
    override suspend fun doWork(): Result {

        try {
            val notificationManager = getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager

            createNotificationChannel(notificationManager)

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Habits")
                .setContentText("Habits refreshed")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build()

            notificationManager.notify(NOTIFICATION_ID, notification)

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

        } catch (e: Exception) {
            Log.e("UpdatingHabitWorker", "Error updating habits", e)
            return Result.failure()
        } finally {
            val notificationManager = getSystemService(
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

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
        const val UPDATING_WORKER_TAG = "UpdatingHabitWorkerTag"

        fun createWorkRequest(data: Data): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(UpdatingHabitWorker::class.java)
                .setInputData(data)
                .addTag(UPDATING_WORKER_TAG)
                .build()
        }

        fun cancelWork(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(UPDATING_WORKER_TAG)
        }
    }
}

//TODO DI of WorkManager
//TODO Notification Text, Title and Icon