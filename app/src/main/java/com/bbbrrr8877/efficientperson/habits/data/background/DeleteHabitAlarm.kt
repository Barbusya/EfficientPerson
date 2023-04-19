package com.bbbrrr8877.efficientperson.habits.data.background

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import com.bbbrrr8877.efficientperson.habits.backgroundwork.HabitDeletingAlarmReceiver
import com.bbbrrr8877.efficientperson.habits.domain.repositories.DeletingHabitBackgroundWork
import java.util.*
import javax.inject.Inject

class DeleteHabitAlarm @Inject constructor() : DeletingHabitBackgroundWork {

    override suspend fun startDeletingAlarmManager(activity: Activity, habitItemId: Long) {
        Log.d("DeletingItem", "alarm manager")
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        //TODO Set 5 days after customizing
        calendar.add(Calendar.SECOND, 4)
        val intent = HabitDeletingAlarmReceiver.newIntent(activity.applicationContext)
        intent.apply {
            putExtra("habit_id_from_alarm", habitItemId)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            activity.applicationContext,
            101,
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
