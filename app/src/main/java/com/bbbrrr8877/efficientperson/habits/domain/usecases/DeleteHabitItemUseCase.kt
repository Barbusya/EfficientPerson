package com.bbbrrr8877.efficientperson.habits.domain.usecases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository

class DeleteHabitItemUseCase(
    private val habitRepository: HabitRepository
) {

    fun deleteHabitItem(habitItem: HabitItem) {
        habitRepository.deleteHabitItem(habitItem)
    }
}