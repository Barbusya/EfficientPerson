package com.bbbrrr8877.efficientperson.habits.domain.UseCases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository

class AddHabitItemUseCase(
    private val habitRepository: HabitRepository
) {

    suspend fun addHabitItem(habitItem: HabitItem) {
        habitRepository.addHabitItem(habitItem)
    }
}