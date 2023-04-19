package com.bbbrrr8877.efficientperson.habits.domain.usecases

import android.app.Activity
import com.bbbrrr8877.efficientperson.habits.domain.repositories.UpdatingHabitBackgroundWork
import javax.inject.Inject

/**
 * Set AlarmManager at 23:59 which start Broadcast Receiver which start WorkManager.
 */

class SetUpdatingHabitsByDoneUseCase @Inject constructor(
    private val updatingHabitBackgroundWork: UpdatingHabitBackgroundWork
) {

    suspend fun startAlarmManager(activity: Activity) {
        updatingHabitBackgroundWork.startUpdatingAlarmManager(activity)
    }
}