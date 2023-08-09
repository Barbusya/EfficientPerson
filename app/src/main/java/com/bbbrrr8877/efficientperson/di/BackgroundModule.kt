package com.bbbrrr8877.efficientperson.di

import com.bbbrrr8877.efficientperson.habits.backgroundwork.HabitsBackgroundWorkImpl
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitsBackgroundWork
import dagger.Binds
import dagger.Module

@Module
interface BackgroundModule {

    @ApplicationScope
    @Binds
    fun bindSetBackgroundWork(impl: HabitsBackgroundWorkImpl): HabitsBackgroundWork
}