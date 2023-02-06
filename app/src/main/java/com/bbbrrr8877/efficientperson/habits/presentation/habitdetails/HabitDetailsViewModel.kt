package com.bbbrrr8877.efficientperson.habits.presentation.habitdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.AddHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.EditHabitItemUseCase
import com.bbbrrr8877.efficientperson.habits.domain.UseCases.GetHabitItemUseCase
import kotlinx.coroutines.launch

class HabitDetailsViewModel(
    private val getHabitItemUseCase: GetHabitItemUseCase,
    private val addHabitItemUseCase: AddHabitItemUseCase,
    private val editHabitItemUseCase: EditHabitItemUseCase,
) : ViewModel() {

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
        inputSwitcher: Boolean,
        inputChecker: Boolean,
    ) {
        val title = parseTitle(inputTitle)
        val description = parseDescription(inputDescription)
        val switcher = inputSwitcher
        val checker = inputChecker
        val fieldsValid = validateInput(title)
        if (fieldsValid) {
            viewModelScope.launch {
                val habitItem = HabitItem(title, description, switcher, checker)
                addHabitItemUseCase.addHabitItem(habitItem)
                finishWork()
            }
        }
    }

    fun editHabitItem(
        inputTitle: String?,
        inputDescription: String?,
        inputSwitcher: Boolean,
        inputChecker: Boolean,
    ) {
        val title = parseTitle(inputTitle)
        val description = parseDescription(inputDescription)
        val switcher = inputSwitcher
        val checker = inputChecker
        val fieldsValid = validateInput(title)
        if (fieldsValid) {
            _habitItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(
                        title = title,
                        description = description,
                        isGood = switcher,
                        isDone = checker
                    )
                    editHabitItemUseCase.editHabitItem(item)
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

    fun resetErrorInputName() {
        _errorInputTitle.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
