package com.bbbrrr8877.efficientperson.habits.domain.usecases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHabitItemUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {

    fun getHabitItem(habitItemId: Long): Flow<HabitItem> {
        return habitRepository.getHabitItem(habitItemId)
    }
}