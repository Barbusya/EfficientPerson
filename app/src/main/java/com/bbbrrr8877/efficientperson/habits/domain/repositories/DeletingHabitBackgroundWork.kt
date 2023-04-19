package com.bbbrrr8877.efficientperson.habits.domain.repositories

import android.app.Activity

interface DeletingHabitBackgroundWork {

    suspend fun startDeletingAlarmManager(activity: Activity, habitItemId: Long)
}
