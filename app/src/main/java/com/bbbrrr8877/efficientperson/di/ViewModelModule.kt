package com.bbbrrr8877.efficientperson.di

import androidx.lifecycle.ViewModel
import com.bbbrrr8877.efficientperson.habits.presentation.habitdetails.HabitDetailsViewModel
import com.bbbrrr8877.efficientperson.habits.presentation.habitlist.HabitListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HabitListViewModel::class)
    fun bindHabitListViewModel(viewModel: HabitListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HabitDetailsViewModel::class)
    fun bindHabitDetailsViewModel(viewModel: HabitDetailsViewModel): ViewModel
}