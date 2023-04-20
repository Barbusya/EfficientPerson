package com.bbbrrr8877.efficientperson.habits.alarms

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.bbbrrr8877.efficientperson.habits.backgroundwork.HabitResetingAlarmReceiver
import com.bbbrrr8877.efficientperson.habits.domain.repositories.UpdatingHabitBackgroundWork
import java.util.*
import javax.inject.Inject

class ResetHabitStatusAlarm @Inject constructor() : UpdatingHabitBackgroundWork {

    override suspend fun startUpdatingAlarmManager(activity: Activity) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        //TODO Return this
//            calendar.set(Calendar.HOUR_OF_DAY, 0)
//            calendar.set(Calendar.MINUTE, 20)
//            calendar.set(Calendar.SECOND, 0)
        calendar.add(Calendar.SECOND, 4)
        val intent = HabitResetingAlarmReceiver.newIntent(activity.applicationContext)
        val pendingIntent = PendingIntent.getBroadcast(
            activity.applicationContext,
            100,
            intent,
            0
        )
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}
