package com.bbbrrr8877.efficientperson.habits.domain.usecases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository

class EditHabitItemUseCase(
    private val habitRepository: HabitRepository
) {

    fun editHabitItem(habitItem: HabitItem) {
        habitRepository.editHabitItem(habitItem)
    }
}