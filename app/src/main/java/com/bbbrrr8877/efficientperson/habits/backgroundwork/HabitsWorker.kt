package com.bbbrrr8877.efficientperson.habits.backgroundwork

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HabitsWorker(
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

                    }
                    notificationManager.cancel(NOTIFICATION_ID)
                }
            return Result.success()

        } catch (e: Exception) {
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
        const val HABITS_WORKER_TAG = "HabitsWorkerTag"

        fun createWorkRequest(timeDiff: Long): PeriodicWorkRequest {
            return PeriodicWorkRequest.Builder(HabitsWorker::class.java, 1, TimeUnit.DAYS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag(HABITS_WORKER_TAG)
                .build()
        }

        fun cancelWork(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(HABITS_WORKER_TAG)
        }
    }

    class Factory @Inject constructor(
        private val habitDatabase: HabitDatabase.Companion,
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return HabitsWorker(
                context, workerParameters, habitDatabase
            )
        }
    }
}
//TODO Notification Text, Title and Icon