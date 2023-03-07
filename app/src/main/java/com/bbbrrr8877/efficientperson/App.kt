package com.bbbrrr8877.efficientperson

import android.app.Application
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import com.bbbrrr8877.efficientperson.di.DaggerApplicationComponent
import com.bbbrrr8877.efficientperson.habits.backgroundwork.MyWorkerFactory
import com.bbbrrr8877.efficientperson.habits.data.room.HabitDatabase
import com.bbbrrr8877.efficientperson.habits.data.room.HabitListMapper

class App : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration {

        val myWorkerFactory = DelegatingWorkerFactory()
        myWorkerFactory.addFactory(MyWorkerFactory(HabitDatabase))

        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(myWorkerFactory)
            .build()
    }


    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}