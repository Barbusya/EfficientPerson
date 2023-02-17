package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.icu.util.Calendar
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.usecases.DeleteHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.GetHabitListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitListViewModel @Inject constructor(
    private val getHabitListUseCase: GetHabitListUseCase,
    private val deleteHabitListUseCase: DeleteHabitItemUseCase,
    private val editHabitListUseCase: EditHabitItemUseCase,
) : ViewModel() {


    val habitList = getHabitListUseCase.getHabitList()

    fun deleteHabitItem(habitItem: HabitItem) {
        viewModelScope.launch {
            deleteHabitListUseCase.deleteHabitItem(habitItem)
        }
    }

    fun changeDoneState(habitItem: HabitItem) {
        viewModelScope.launch {
            val newItem = habitItem.copy(
                isDone = !habitItem.isDone,
            )
            editHabitListUseCase.editHabitItem(newItem)
        }
    }

    companion object {
        private const val DAY_OF_DONE_UNKNOWN = 1
    }
}
