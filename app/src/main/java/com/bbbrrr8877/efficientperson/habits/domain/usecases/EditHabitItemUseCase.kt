package com.bbbrrr8877.efficientperson.habits.domain.usecases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import javax.inject.Inject

class EditHabitItemUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {

    suspend fun editHabitItem(habitItem: HabitItem) {
        habitRepository.editHabitItem(habitItem)
    }
}