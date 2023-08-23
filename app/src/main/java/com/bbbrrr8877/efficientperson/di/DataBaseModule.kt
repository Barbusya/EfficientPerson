package com.bbbrrr8877.efficientperson.di

import android.app.Application
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase
import com.bbbrrr8877.efficientperson.habits.data.room.HabitListDao
import com.bbbrrr8877.efficientperson.habits.data.room.HabitRepositoryImpl
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataBaseModule {

    @ApplicationScope
    @Binds
    fun bindHabitListRepository(impl: HabitRepositoryImpl): HabitRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideHabitListDao(
            application: Application
        ): HabitListDao {
            return HabitDatabase.getInstance(application).habitListDao()
        }

        @ApplicationScope
        @Provides
        fun provideHabitsDataBase(): HabitDatabase.Companion {
            return HabitDatabase.Companion
        }
    }
}