package com.bbbrrr8877.efficientperson.habits.domain.UseCases

import androidx.lifecycle.LiveData
import com.bbbrrr8877.efficientperson.habits.domain.Etities.HabitItem
import com.bbbrrr8877.efficientperson.habits.domain.repositories.HabitRepository

class GetHabitListUseCase(
    private val habitRepository: HabitRepository
) {

    fun getHabitList(): LiveData<List<HabitItem>> {
        return habitRepository.getHabitList()
    }
}