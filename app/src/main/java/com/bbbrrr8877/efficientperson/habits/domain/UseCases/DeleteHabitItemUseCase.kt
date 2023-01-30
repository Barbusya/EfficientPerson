package com.bbbrrr8877.efficientperson.habits.domain.UseCases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository

class DeleteHabitItemUseCase(
    private val habitRepository: HabitRepository
) {

    suspend fun deleteHabitItem(habitItem: HabitItem) {
        habitRepository.deleteHabitItem(habitItem)
    }
}