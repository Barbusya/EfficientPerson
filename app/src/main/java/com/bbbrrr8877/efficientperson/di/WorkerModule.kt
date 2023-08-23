package com.bbbrrr8877.efficientperson.di

import com.bbbrrr8877.efficientperson.habits.backgroundwork.ChildWorkerFactory
import com.bbbrrr8877.efficientperson.habits.backgroundwork.HabitsWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(HabitsWorker::class)
    fun bindsHabitsWorkerFactory(worker: HabitsWorker.Factory): ChildWorkerFactory
}