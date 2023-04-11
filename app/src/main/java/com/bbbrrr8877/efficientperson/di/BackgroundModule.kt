package com.bbbrrr8877.efficientperson.di

import com.bbbrrr8877.efficientperson.habits.data.background.ResetHabitStatus
import com.bbbrrr8877.efficientperson.habits.domain.repositories.SetBackgroundWork
import dagger.Binds
import dagger.Module

@Module
interface BackgroundModule {

    @ApplicationScope
    @Binds
    fun bindRestHabitStatus(impl: ResetHabitStatus): SetBackgroundWork
}