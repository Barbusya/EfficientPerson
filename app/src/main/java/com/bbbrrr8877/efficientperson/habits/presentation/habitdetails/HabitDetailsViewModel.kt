package com.bbbrrr8877.efficientperson.habits.presentation.habitdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bbbrrr8877.efficientperson.habits.data.room.HabitRepositoryImpl
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.usecases.AddHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.usecases.GetHabitItemUseCase
import kotlinx.coroutines.launch

class HabitDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HabitRepositoryImpl(application)

    private val getHabitItemUseCase = GetHabitItemUseCase(repository)
    private val addHabitListUseCase = AddHabitItemUseCase(repository)
    private val editHabitListUseCase = EditHabitItemUseCase(repository)

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean> = _errorInputTitle

    private val _habitItem = MutableLiveData<HabitItem>()
    val habitItem: LiveData<HabitItem> = _habitItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    fun getHabitItem(habitItemId: Long) {
        viewModelScope.launch {
            val item = getHabitItemUseCase.getHabitItem(habitItemId)
            _habitItem.value = item
        }
    }

    fun addHabitItem(
        inputTitle: String?,
        inputDescription: String?,
        inputQuality: Boolean,
        inputProgress: Boolean
    ) {
        val title = parseTitle(inputTitle)
        val description = parseDescription(inputDescription)
        val fieldValid = validateInput(title)
        if (fieldValid) {
            viewModelScope.launch {
                val habitItem = HabitItem(
                    title = title,
                    description = description,
                    isGood = inputQuality,
                    isDone = inputProgress
                )
                addHabitListUseCase.addHabitItem(habitItem)
                finishWork()
            }
        }
    }

    fun editHabitItem(
        inputTitle: String?,
        inputDescription: String?,
        inputProgress: Boolean
    ) {
        val title = parseTitle(inputTitle)
        val description = parseDescription(inputDescription)
        val fieldValid = validateInput(title)
        if (fieldValid) {
            _habitItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(
                        title = title,
                        description = description,
                        isDone = inputProgress
                    )
                    editHabitListUseCase.editHabitItem(item)
                    finishWork()
                }
            }
        }
    }


    private fun parseTitle(inputTitle: String?): String {
        return inputTitle?.trim() ?: ""
    }

    private fun parseDescription(inputDescription: String?): String {
        return inputDescription?.trim() ?: ""
    }

    private fun validateInput(title: String): Boolean {
        var result = true
        if (title.isBlank()) {
            _errorInputTitle.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputTitle() {
        _errorInputTitle.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
