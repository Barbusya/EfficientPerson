package com.bbbrrr8877.efficientperson.habits.data.backgroundwork

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bbbrrr8877.efficientperson.R
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase
import kotlinx.coroutines.*

class HabitForegroundService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        coroutineScope.launch {
//            for (i in 0 until 3) {
//                delay(1000)
//                log("Timer $i")
//            }
//            stopSelf()
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
                    stopSelf()
                }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        log("onDestroy")
        coroutineScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("ForegroundService")
        .setContentText("Text")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .build()


    private fun log(message: String) {
        Log.d("HabitForegroundService", "HabitForegroundService $message")
    }


    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        fun newIntent(context: Context): Intent {
            return Intent(context, HabitForegroundService::class.java)
        }
    }
}