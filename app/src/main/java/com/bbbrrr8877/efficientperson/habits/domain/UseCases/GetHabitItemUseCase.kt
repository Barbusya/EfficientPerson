package com.bbbrrr8877.efficientperson.habits.domain.UseCases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository

class GetHabitItemUseCase(
    private val habitRepository: HabitRepository
) {

    fun getHabitItem(habitItemId: Long): HabitItem {
        return habitRepository.getHabitItem(habitItemId)
    }
}