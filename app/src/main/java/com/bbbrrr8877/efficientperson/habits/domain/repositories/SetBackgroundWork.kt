package com.bbbrrr8877.efficientperson.habits.domain.repositories

import android.app.Activity

interface SetBackgroundWork {

    suspend fun startAlarmManager(activity: Activity)
}
