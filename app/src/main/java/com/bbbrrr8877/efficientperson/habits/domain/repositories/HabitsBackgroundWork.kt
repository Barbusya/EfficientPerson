package com.bbbrrr8877.efficientperson.habits.domain.repositories

import android.app.Activity

interface HabitsBackgroundWork {

    suspend fun startHabitsWorkManager(activity: Activity)
}