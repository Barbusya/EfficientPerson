package com.bbbrrr8877.efficientperson.di

import com.bbbrrr8877.efficientperson.habits.data.background.DeleteHabit
import com.bbbrrr8877.efficientperson.habits.data.background.ResetHabitStatus
import com.bbbrrr8877.efficientperson.habits.domain.repositories.UpdatingHabitBackgroundWork
import dagger.Binds
import dagger.Module

@Module
interface BackgroundModule {

    @ApplicationScope
    @Binds
    fun bindResetHabitStatus(impl: ResetHabitStatus): UpdatingHabitBackgroundWork

    @ApplicationScope
    @Binds
    fun bindDeleteHabit(impl: DeleteHabit): UpdatingHabitBackgroundWork
}