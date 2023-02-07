package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import androidx.lifecycle.ViewModel
import com.bbbrrr8877.efficientperson.habits.data.room.HabitRepositoryImpl
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.DeleteHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.GetHabitListUseCase

class HabitListViewModel : ViewModel() {

    private val repository = HabitRepositoryImpl

    private val getHabitListUseCase = GetHabitListUseCase(repository)
    private val deleteHabitListUseCase = DeleteHabitItemUseCase(repository)
    private val editHabitListUseCase = EditHabitItemUseCase(repository)

    val habitList = getHabitListUseCase.getHabitList()

    fun deleteHabitItem(habitItem: HabitItem) {
        deleteHabitListUseCase.deleteHabitItem(habitItem)
    }

    fun changeDoneState(habitItem: HabitItem) {
        val newItem = habitItem.copy(isDone = !habitItem.isDone)
        editHabitListUseCase.editHabitItem(newItem)
    }
}
