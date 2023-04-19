package com.bbbrrr8877.efficientperson.habits.domain.usecases

import android.app.Activity
import android.util.Log
import com.bbbrrr8877.efficientperson.habits.domain.repositories.DeletingHabitBackgroundWork
import javax.inject.Inject

/**
 * Set AlarmManager at 29:00 which start Broadcast Receiver which start WorkManager.
 */

class DeletingHabitItemUseCase @Inject constructor(
    private val deletingHabitBackgroundWork: DeletingHabitBackgroundWork
) {

    suspend fun startDeletingAlarmManager(activity: Activity, habitItemId: Long) {
        deletingHabitBackgroundWork.startDeletingAlarmManager(activity, habitItemId)
        Log.d("DeletingItem", "use case")
    }
}