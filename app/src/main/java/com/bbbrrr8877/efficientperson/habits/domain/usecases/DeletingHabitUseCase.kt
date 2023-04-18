package com.bbbrrr8877.efficientperson.habits.domain.usecases

import android.app.Activity
import com.bbbrrr8877.efficientperson.habits.domain.repositories.UpdatingHabitBackgroundWork
import javax.inject.Inject

/**
 * Set AlarmManager at 29:00 which start Broadcast Receiver which start WorkManager.
 */

class DeletingHabitUseCase @Inject constructor(
    private val updatingHabitBackgroundWork: UpdatingHabitBackgroundWork
) {

    suspend fun startDeletingAlarmManager(activity: Activity) {
        updatingHabitBackgroundWork.startAlarmManager(activity)
    }
}