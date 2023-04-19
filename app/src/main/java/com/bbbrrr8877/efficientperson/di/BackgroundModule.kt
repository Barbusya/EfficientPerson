package com.bbbrrr8877.efficientperson.di

import com.bbbrrr8877.efficientperson.habits.data.background.DeleteHabitAlarm
import com.bbbrrr8877.efficientperson.habits.data.background.ResetHabitStatusAlarm
import com.bbbrrr8877.efficientperson.habits.domain.repositories.DeletingHabitBackgroundWork
import com.bbbrrr8877.efficientperson.habits.domain.repositories.UpdatingHabitBackgroundWork
import dagger.Binds
import dagger.Module

@Module
interface BackgroundModule {

    @ApplicationScope
    @Binds
    fun bindResetHabitStatus(impl: ResetHabitStatusAlarm): UpdatingHabitBackgroundWork

    @ApplicationScope
    @Binds
    fun bindDeleteHabit(impl: DeleteHabitAlarm): DeletingHabitBackgroundWork
}