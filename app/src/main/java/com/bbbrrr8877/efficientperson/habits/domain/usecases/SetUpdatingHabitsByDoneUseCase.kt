package com.bbbrrr8877.efficientperson.habits.domain.usecases

import android.app.Activity
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitsBackgroundWork
import javax.inject.Inject

/**
 * Set AlarmManager at 29:00 which start Broadcast Receiver which start WorkManager.
 */

class SetUpdatingHabitsByDoneUseCase @Inject constructor(
    private val habitsBackgroundWork: HabitsBackgroundWork
) {

    suspend fun startHabitsWorkManager(activity: Activity) {
        habitsBackgroundWork.startHabitsWorkManager(activity)
    }
}