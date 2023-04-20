package com.bbbrrr8877.efficientperson.di

import com.bbbrrr8877.efficientperson.habits.alarms.ResetHabitStatusAlarm
import com.bbbrrr8877.efficientperson.habits.domain.repositories.UpdatingHabitBackgroundWork
import dagger.Binds
import dagger.Module

@Module
interface BackgroundModule {

    @ApplicationScope
    @Binds
    fun bindResetHabitStatus(impl: ResetHabitStatusAlarm): UpdatingHabitBackgroundWork

}