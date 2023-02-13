package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bbbrrr8877.efficientperson.habits.data.room.HabitRepositoryImpl
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.usecases.DeleteHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.GetHabitListUseCase
import kotlinx.coroutines.launch

class HabitListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HabitRepositoryImpl(application)

    private val getHabitListUseCase = GetHabitListUseCase(repository)
    private val deleteHabitListUseCase = DeleteHabitItemUseCase(repository)
    private val editHabitListUseCase = EditHabitItemUseCase(repository)

    val habitList = getHabitListUseCase.getHabitList()

    fun deleteHabitItem(habitItem: HabitItem) {
        viewModelScope.launch {
            deleteHabitListUseCase.deleteHabitItem(habitItem)
        }
    }

    fun changeDoneState(habitItem: HabitItem) {
        viewModelScope.launch {
            val newItem = habitItem.copy(isDone = !habitItem.isDone)
            editHabitListUseCase.editHabitItem(newItem)
        }
    }
}
