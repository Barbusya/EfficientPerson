package com.bbbrrr8877.efficientperson.habits.domain.usecases

import android.app.Activity
import com.bbbrrr8877.efficientperson.habits.domain.repositories.SetBackgroundWork
import javax.inject.Inject

/**
 * Set AlarmManager at 29:00 which start Broadcast Receiver which start WorkManager.
 */

class SetUpdatingHabitsByDoneUseCase @Inject constructor(
    private val setBackgroundWork: SetBackgroundWork
) {

    suspend fun startAlarmManager(activity: Activity) {
        setBackgroundWork.startAlarmManager(activity)
    }
}