package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.DeleteHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.GetHabitListUseCase
import kotlinx.coroutines.launch

class HabitListViewModel(
    private val getHabitListUseCase: GetHabitListUseCase,
    private val deleteHabitItemUseCase: DeleteHabitItemUseCase,
    private val editHabitItemUseCase: EditHabitItemUseCase,
): ViewModel() {

    val habitList = getHabitListUseCase.getHabitList()

    fun deleteHabitItem (habitItem: HabitItem) {
        viewModelScope.launch {
            deleteHabitItemUseCase.deleteHabitItem(habitItem)
        }
    }

    fun editHabitItem(habitItem: HabitItem) {
        viewModelScope.launch {
            editHabitItemUseCase.editHabitItem(habitItem)
        }
    }
}
