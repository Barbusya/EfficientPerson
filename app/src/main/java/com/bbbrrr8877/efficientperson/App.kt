package com.bbbrrr8877.efficientperson

import android.app.Application
import androidx.work.Configuration
import com.bbbrrr8877.efficientperson.di.DaggerApplicationComponent
import com.bbbrrr8877.efficientperson.habits.backgroundwork.HabitsWorkerFactory
import javax.inject.Inject

class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HabitsWorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {

        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(workerFactory)
            .build()
    }


    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}