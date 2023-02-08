package com.bbbrrr8877.efficientperson.habits.presentation.habitdetails

import androidx.lifecycle.ViewModel
import com.bbbrrr8877.efficientperson.habits.data.room.HabitRepositoryImpl
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.usecases.*

class HabitDetailsViewModel: ViewModel() {

    private val repository = HabitRepositoryImpl

    private val getHabitItemUseCase = GetHabitItemUseCase(repository)
    private val addHabitListUseCase = AddHabitItemUseCase(repository)
    private val editHabitListUseCase = EditHabitItemUseCase(repository)

    fun getHabitItem(habitItemId: Long) {
        val item = getHabitItemUseCase.getHabitItem(habitItemId)
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
            val habitItem = HabitItem(
                title = title,
                description = description,
                isGood = inputQuality,
                isDone = inputProgress
            )
            addHabitListUseCase.addHabitItem(habitItem)
        }
    }

    fun editHabitItem(
        inputTitle: String?,
        inputDescription: String?,
        inputQuality: Boolean,
        inputProgress: Boolean
    ) {
        val title = parseTitle(inputTitle)
        val description = parseDescription(inputDescription)
        val fieldValid = validateInput(title)
        if (fieldValid) {
            val habitItem = HabitItem(
                title = title,
                description = description,
                isGood = inputQuality,
                isDone = inputProgress
            )
            editHabitListUseCase.editHabitItem(habitItem)
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
            // TODO: show error input name
            result = false
        }
        return result
    }
}
