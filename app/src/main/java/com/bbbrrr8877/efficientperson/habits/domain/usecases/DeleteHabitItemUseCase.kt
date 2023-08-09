package com.bbbrrr8877.efficientperson.habits.domain.usecases

import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import javax.inject.Inject

class DeleteHabitItemUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {

    suspend fun deleteHabitItem(habitItemId: Long) {
        habitRepository.deleteHabitItem(habitItemId)
    }
}