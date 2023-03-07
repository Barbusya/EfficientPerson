package com.bbbrrr8877.efficientperson.di

import android.app.Application
import com.bbbrrr8877.efficientperson.MainActivity
import com.bbbrrr8877.efficientperson.habits.presentation.habitdetails.HabitDetailsFragment
import com.bbbrrr8877.efficientperson.habits.presentation.habitlist.HabitListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataBaseModule::class,
        ViewModelModule::class,
        BackgroundModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: HabitDetailsFragment)

    fun inject(fragment: HabitListFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}