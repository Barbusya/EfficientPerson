package com.bbbrrr8877.efficientperson.habits.domain.usecases

import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository
import javax.inject.Inject

class  AddHabitItemUseCase @Inject constructor(
    private val habitListRepository: HabitRepository
) {

    suspend fun addHabitItem(habitItem: HabitItem) {
        habitListRepository.addHabitItem(habitItem)
    }
}