package com.bbbrrr8877.efficientperson.habits.presentation.habitlist

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.usecases.*
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitListViewModel @Inject constructor(
    private val getHabitListUseCase: GetHabitListUseCase,
    private val deleteHabitItemUseCase: DeleteHabitItemUseCase,
    private val editHabitItemUseCase: EditHabitItemUseCase,
    private val setUpdatingHabitsByDoneUseCase: SetUpdatingHabitsByDoneUseCase,
    private val deletingHabitItemUseCase: DeletingHabitItemUseCase,
) : ViewModel() {

    val habitList = getHabitListUseCase.getHabitList()
        .filter { it.isNotEmpty() }
        .onEach { }
        .asLiveData()

    fun deleteHabitItem(habitItem: HabitItem) {
        viewModelScope.launch {
            deleteHabitItemUseCase.deleteHabitItem(habitItem)
        }
    }

    fun changeDoneState(habitItem: HabitItem) {
        viewModelScope.launch {
            val newItem = habitItem.copy(
                isDone = !habitItem.isDone,
            )
            editHabitItemUseCase.editHabitItem(newItem)
        }
    }

    fun setUpdatingHabitsByDone(activity: Activity) {
        viewModelScope.launch {
            setUpdatingHabitsByDoneUseCase.startAlarmManager(activity)
        }
    }

    fun setDeletingHabit(activity: Activity, habitItemId: Long) {
        viewModelScope.launch {
            deletingHabitItemUseCase.startDeletingAlarmManager(activity, habitItemId)
            Log.d("DeletingItem", "view model")
        }
    }

}
